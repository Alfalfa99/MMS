package Echoiot.alfalfa.MMS.controller;


import Echoiot.alfalfa.MMS.exception.VerifyException;
import Echoiot.alfalfa.MMS.model.bean.CommonResult;
import Echoiot.alfalfa.MMS.model.bean.VerifyCode;
import Echoiot.alfalfa.MMS.model.dto.UserLoginDTO;
import Echoiot.alfalfa.MMS.model.dto.UserRegisterDTO;
import Echoiot.alfalfa.MMS.model.dto.UserUpdateDTO;
import Echoiot.alfalfa.MMS.model.dto.UserUpdatePwdDTO;
import Echoiot.alfalfa.MMS.model.pojo.User;
import Echoiot.alfalfa.MMS.service.UserService;
import Echoiot.alfalfa.MMS.utils.VerifyCodeUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author Alfalfa99
 * @Date 2020/9/13 13:54
 * @Version 1.0
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RedisTemplate redisTemplate;

    public UserController(UserService userService, RedisTemplate redisTemplate) {
        this.userService = userService;
        this.redisTemplate = redisTemplate;
    }


    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody UserLoginDTO ulDTO, HttpServletRequest request) {
        if (null == redisTemplate.opsForValue().get("vc_" + ulDTO.getVerifyCode())) {
            throw new VerifyException("验证码错误");
        }
        redisTemplate.delete("vc_" + ulDTO.getVerifyCode());
        String token = userService.userLogin(ulDTO);

        return new CommonResult<>(20000, "OK", token);
    }

    @PostMapping("/register")
    public CommonResult<String> register(@RequestBody UserRegisterDTO urDTO) {
        if (null == redisTemplate.opsForValue().get("vc_" + urDTO.getVerifyCode())) {
            throw new VerifyException("验证码错误");
        }
        userService.userRegister(urDTO);
        return new CommonResult<>(20000, "OK", "注册成功");
    }

    @GetMapping("/detail/{id}")
    public CommonResult<User> getUserDetailById(@PathVariable("id") String id, HttpServletRequest request) {
        String uid = (String) request.getAttribute("uid");
        User user = userService.getUserDetailById(id);
        if (user != null) {
            if (!id.equals(uid)) {
                user.setPhone("");
                user.setEmail("");
                user.setUsername("");
                user.setId("");
                user.setPassword("");
            } else {
                this.getUserDetail(request);
            }
        }
        return new CommonResult<>(20000, "OK", user);
    }
    @RequestMapping
    @GetMapping("/detail")
    public CommonResult<User> getUserDetail(HttpServletRequest request) {
        String uid = (String) request.getAttribute("uid");
        User user = userService.getUserDetailById(uid);
        user.setPassword("");
        return new CommonResult<>(20000, "OK", user);
    }

    @PutMapping("/update")
    public CommonResult<User> updateUserDetail(@RequestBody UserUpdateDTO uuDTO, HttpServletRequest request) {
        String uid = (String) request.getAttribute("uid");
        User user = userService.updateUserDetail(uuDTO, uid);
        return new CommonResult<>(20000, "修改成功", user);
    }

    @PutMapping("/updatePWD")
    public CommonResult<String> updateUserPwd(@RequestBody UserUpdatePwdDTO uuPwdDTO, HttpServletRequest request) {
        String uid = (String) request.getAttribute("uid");
        userService.updateUserPwd(uuPwdDTO,uid);
        return new CommonResult<>(20000, "OK","修改成功");
    }

    @PostMapping("/verifyCode")
    public void verifyCode(HttpServletResponse response) throws IOException {
        VerifyCodeUtil verifyCodeUtil = new VerifyCodeUtil();
        //设置长宽
        VerifyCode verifyCode = verifyCodeUtil.generate(80, 28);
        String code = verifyCode.getCode();
        //将VerifyCode写入redis
        redisTemplate.opsForValue().set("vc_" + code, "1", 300, TimeUnit.SECONDS);
        //设置响应头
        response.setHeader("Pragma", "no-cache");
        //设置响应头
        response.setHeader("Cache-Control", "no-cache");
        //在代理服务器端防止缓冲
        response.setDateHeader("Expires", 0);
        //设置响应内容类型
        response.setContentType("image/jpeg");
        response.getOutputStream().write(verifyCode.getImgBytes());
        response.getOutputStream().flush();
    }
}

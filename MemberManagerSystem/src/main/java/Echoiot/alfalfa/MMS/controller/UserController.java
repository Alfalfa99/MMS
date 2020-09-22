package Echoiot.alfalfa.MMS.controller;
import Echoiot.alfalfa.MMS.model.bean.CommonResult;
import Echoiot.alfalfa.MMS.model.pojo.User;
import Echoiot.alfalfa.MMS.service.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public CommonResult<String> register(@RequestBody User user) {
        userService.userRegister(user);
        return new CommonResult<>(20000, "OK", "注册成功");
    }

    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody User user) {
        String res = userService.userLogin(user);
        return new CommonResult<>(20000, "OK", res);
    }
}

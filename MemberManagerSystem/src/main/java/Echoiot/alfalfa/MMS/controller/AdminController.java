package Echoiot.alfalfa.MMS.controller;


import Echoiot.alfalfa.MMS.model.bean.CommonResult;
import Echoiot.alfalfa.MMS.model.bean.PageResult;
import Echoiot.alfalfa.MMS.model.dto.PageParamDTO;
import Echoiot.alfalfa.MMS.model.dto.TaskDTO;
import Echoiot.alfalfa.MMS.model.pojo.ApplicationForm;
import Echoiot.alfalfa.MMS.model.pojo.User;
import Echoiot.alfalfa.MMS.service.AdminService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author Alfalfa99
 * @Date 2020/9/16 19:55
 * @Version 1.0
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping("/findAllUser")
    public CommonResult<PageResult<User>> findAllUser(@Valid @RequestBody PageParamDTO ppDTO) {
        PageResult<User> allUser = adminService.findAllUser(ppDTO);
        return new CommonResult<>(20000, "OK", allUser);
    }

    @PostMapping("/findUserBy/{grade}/{role}")
    public CommonResult<PageResult<User>> findUserByYearAndRole(@PathVariable("grade") String grade,
                                                                @PathVariable("role") String role,
                                                                @Valid @RequestBody PageParamDTO ppDTO) {
        PageResult<User> userByYearAndRole = adminService.findUserByYearAndRole(grade, role,
                ppDTO);
        return new CommonResult<>(20000, "OK", userByYearAndRole);
    }

    @PostMapping("/findUserBy/{grade}")
    public CommonResult<PageResult<User>> findUserByYear(@PathVariable("grade") String grade,
                                                         @RequestBody PageParamDTO ppDTO) {
        PageResult<User> userByYear = adminService.findUserByYear(grade, ppDTO);
        return new CommonResult<>(20000, "OK", userByYear);
    }

    @PutMapping("/update")
    public CommonResult<User> updateUser(@Validated @RequestBody User user) {
        adminService.updateUser(user);
        return new CommonResult<>(20000, "修改成功", user);
    }

    @PutMapping("/updateRole/{uid}")
    public CommonResult<String> updateUserRole(@PathVariable("uid") String uid,
                                               @RequestParam("roleName") String roleName) {
        adminService.updateUserRole(uid, roleName);
        return new CommonResult<>(20000, "OK", "修改成功");
    }

    @PostMapping("/createTask")
    public CommonResult<String> createTask(@RequestBody TaskDTO taskDTO, HttpServletRequest request) {
        adminService.createTask(taskDTO, (String) request.getAttribute("uid"));
        return new CommonResult<>(20000, "OK", "创建成功");
    }

    @PostMapping("/setTask/{status}/{tid}")
    public CommonResult<String> setTaskStatus(@PathVariable("status") Integer status,
                                              @PathVariable("tid") String tid) {
        adminService.setTaskStatus(status, tid);
        return new CommonResult<>(20000, "OK", "修改成功");
    }

    @PostMapping("/findAllApf")
    public CommonResult<PageResult<ApplicationForm>> findAllApplicationForm(@Valid @RequestBody PageParamDTO ppDTO) {
        PageResult<ApplicationForm> allApplicationForm = adminService.findAllApplicationForm(ppDTO);
        return new CommonResult<>(20000, "OK", allApplicationForm);
    }

    @PostMapping("/findApf/{status}")
    public CommonResult<PageResult<ApplicationForm>> findApplicationFormByStatus(@PathVariable("status") Integer status,
                                                                                 @Valid @RequestBody PageParamDTO ppDTO) {
        PageResult<ApplicationForm> apfByStatus = adminService.findApplicationFormByStatus(status, ppDTO);
        return new CommonResult<>(20000, "OK", apfByStatus);
    }

    @PostMapping("/approveApf/{status}/{appId}")
    public CommonResult<String> approvalApplication(@PathVariable("status") Integer status,
                                                    @PathVariable("appId") String appId, HttpServletRequest request) {
        adminService.approvalApplication(status, (String) request.getAttribute("uid"), appId);
        return new CommonResult<>(20000, "OK", "审批成功");
    }

    @DeleteMapping("/deleteQ/{qid}")
    public CommonResult<String> deleteQuestionById(@PathVariable("qid") String qid) {
        adminService.deleteQuestionByAdmin(qid);
        return new CommonResult<>(20000, "OK", "删除成功");
    }

    @DeleteMapping("/deleteA/{aid}")
    public CommonResult<String> deleteAnswerByAdmin(@PathVariable("aid") String aid) {
        adminService.deleteAnswerByAdmin(aid);
        return new CommonResult<>(20000, "OK", "删除成功");
    }
}

package Echoiot.alfalfa.MMS.controller;


import Echoiot.alfalfa.MMS.model.bean.CommonResult;
import Echoiot.alfalfa.MMS.model.bean.PageResult;
import Echoiot.alfalfa.MMS.model.dto.PageParamDTO;
import Echoiot.alfalfa.MMS.model.pojo.Task;
import Echoiot.alfalfa.MMS.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author Alfalfa99
 * @Date 2020/9/16 19:54
 * @Version 1.0
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/findAllTask")
    public CommonResult<PageResult<Task>> findAllTask(@Valid @RequestBody PageParamDTO ppDTO) {
        PageResult<Task> allTask = taskService.findAllTask(ppDTO);
        return new CommonResult<>(20000, "OK", allTask);
    }

    @PostMapping("/findTaskById/{id}")
    public CommonResult<PageResult<Task>> findTaskById(@PathVariable("id") String id,
                                                       @Valid @RequestBody PageParamDTO ppDTO) {
        PageResult<Task> taskById = taskService.findTaskById(id, ppDTO);
        return new CommonResult<>(20000, "OK", taskById);

    }

    @PostMapping("/findTaskByStatus/{status}")
    public CommonResult<PageResult<Task>> findTaskByStatus(@PathVariable("status") Integer status,
                                                           @Valid @RequestBody PageParamDTO ppDTO) {
        PageResult<Task> taskByStatus = taskService.findTaskByStatus(status, ppDTO);
        return new CommonResult<>(20000, "OK", taskByStatus);
    }

    @PostMapping("/findTaskByBossId/{id}")
    public CommonResult<PageResult<Task>> findTaskByBossId(@PathVariable("id") String id,
                                                           @Valid @RequestBody PageParamDTO ppDTO) {
        PageResult<Task> taskByBossId = taskService.findTaskByBossId(id, ppDTO);
        return new CommonResult<>(20000, "OK", taskByBossId);
    }

    @PostMapping("/findTaskBy/{bid}/{status}")
    public CommonResult<PageResult<Task>> findTaskByBossIdAndStatus(@PathVariable("bid") String bid,
                                                                    @PathVariable("status") Integer status,
                                                                    @Valid @RequestBody PageParamDTO ppDTO) {
        PageResult<Task> taskByBossIdAndStatus = taskService.findTaskByBossIdAndStatus(bid, status, ppDTO);
        return new CommonResult<>(20000, "OK", taskByBossIdAndStatus);
    }

    @PostMapping("/findTaskByMemberId")
    public CommonResult<PageResult<Task>> findTaskByMemberId(@Valid @RequestBody PageParamDTO ppDTO,
                                                             HttpServletRequest request) {
        PageResult<Task> taskByMemberId = taskService.findTaskByMemberId((String)request.getAttribute("uid"),
                ppDTO);
        return new CommonResult<>(20000, "OK", taskByMemberId);
    }

    @PostMapping("/findTaskByMS/{mid}/{status}")
    public CommonResult<PageResult<Task>> findTaskByMemberIdAndStatus(@PathVariable("mid") String mid,
                                                                      @PathVariable("status") Integer status,
                                                                      @Valid @RequestBody PageParamDTO ppDTO) {
        PageResult<Task> taskByMemberIdAndStatus = taskService.findTaskByMemberIdAndStatus(mid, status, ppDTO);
        return new CommonResult<>(20000, "OK", taskByMemberIdAndStatus);
    }
}

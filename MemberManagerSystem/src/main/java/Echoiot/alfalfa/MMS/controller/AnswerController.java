package Echoiot.alfalfa.MMS.controller;


import Echoiot.alfalfa.MMS.model.bean.CommonResult;
import Echoiot.alfalfa.MMS.model.bean.PageResult;
import Echoiot.alfalfa.MMS.model.dto.PageParamDTO;
import Echoiot.alfalfa.MMS.model.pojo.Answer;
import Echoiot.alfalfa.MMS.service.AnswerService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author Alfalfa99
 * @Date 2020/9/16 19:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService aService;

    public AnswerController(AnswerService aService) {
        this.aService = aService;
    }

    @PostMapping("/addAnswer")
    public CommonResult<String> addAnswer(@RequestBody Answer as) {
        aService.addAnswer(as);
        return new CommonResult<>(20000,"OK","添加成功");
    }

    @PostMapping("/deleteA/{qid}")
    public CommonResult<String> deleteAnswerByUser(@PathVariable("qid") String qid, HttpServletRequest req) {
        aService.deleteAnswerByUser(qid,(String) req.getAttribute("uid"));
        return new CommonResult<>(20000,"OK","添加成功");
    }
    @PostMapping("/findA/{qid}")
    public CommonResult<PageResult<Answer>> findAllAnswerByQid(@PathVariable("qid") String qid,
                                                               @Valid @RequestBody PageParamDTO ppDTO) {
        PageResult<Answer> allAnswerByQid = aService.findAllAnswerByQid(qid, ppDTO);
        return new CommonResult<>(20000,"OK",allAnswerByQid);
    }
    @PostMapping("/findAll")
    public CommonResult<PageResult<Answer>> findAllAnswerByQid(@Valid @RequestBody PageParamDTO ppDTO,
                                                             HttpServletRequest request) {
        PageResult<Answer> uid = aService.findAllAnswerByUid((String) request.getAttribute("uid"), ppDTO);
        return new CommonResult<>(20000,"OK", uid);
    }
    @PostMapping("/changeA/{aid}/{count}")
    public CommonResult<String> changeAnswerPopCount(@PathVariable("aid") String aid,
                                                               @PathVariable("count") Integer count) {
        aService.changeAnswerPopCount(count,aid);
        return new CommonResult<>(20000,"OK","操作成功");
    }
}

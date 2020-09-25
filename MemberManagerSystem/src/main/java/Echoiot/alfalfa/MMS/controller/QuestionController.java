package Echoiot.alfalfa.MMS.controller;


import Echoiot.alfalfa.MMS.model.bean.CommonResult;
import Echoiot.alfalfa.MMS.model.bean.PageResult;
import Echoiot.alfalfa.MMS.model.dto.PageParamDTO;
import Echoiot.alfalfa.MMS.model.pojo.Question;
import Echoiot.alfalfa.MMS.service.QuestionService;
import Echoiot.alfalfa.MMS.utils.PageParamCheckUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author Alfalfa99
 * @Date 2020/9/16 19:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService qService;

    private final PageParamCheckUtil ppCheck;

    public QuestionController(QuestionService qService, PageParamCheckUtil ppCheck) {
        this.qService = qService;
        this.ppCheck = ppCheck;
    }

    @PostMapping("/addQ")
    public CommonResult<Question> addQuestion(@RequestBody Question q, HttpServletRequest request) {
        q.setUid((String) request.getAttribute("uid"));
        qService.addQuestion(q);
        return new CommonResult<>(20000,"OK", q);
    }
    @DeleteMapping("/deleteQ/{qid}")
    public CommonResult<String> deleteQuestionByUser(@PathVariable("qid") String qid, HttpServletRequest request) {
        qService.deleteQuestionByUser(qid,(String) request.getAttribute("uid"));
        return new CommonResult<>(20000,"OK", "删除成功");
    }

    @PostMapping("/findAllQ")
    public CommonResult<PageResult<Question>> findAllQuestion(@Valid @RequestBody PageParamDTO ppDTO) {
        PageResult<Question> allQuestion = qService.findAllQuestion(ppDTO);
        return new CommonResult<>(20000,"OK", allQuestion);
    }
    @PostMapping("/findQ/{qid}")
    public CommonResult<Question> findQuestionById(@PathVariable("qid") String qid) {
        Question questionById = qService.findQuestionById(qid);
        return new CommonResult<>(20000,"OK", questionById);
    }
    @PostMapping("/findQ")
    public CommonResult<PageResult<Question>> findQuestionByUid(@Valid @RequestBody PageParamDTO ppDTO,
                                                                HttpServletRequest request) {
        PageResult<Question> uid = qService.findQuestionByUid((String) request.getAttribute("uid"), ppDTO);
        return new CommonResult<>(20000,"OK", uid);
    }
    @PostMapping("/findQBT/{title}")
    public CommonResult<PageResult<Question>> findQuestionByTitle(@Valid @RequestBody PageParamDTO ppDTO,
                                                                  @PathVariable("title") String title) {
        PageResult<Question> questionByTitle = qService.findQuestionByTitle(title, ppDTO);
        return new CommonResult<>(20000,"OK", questionByTitle);
    }
    @PostMapping("/changeQPC/{qid}/{count}")
    public CommonResult<String> changeQuestionPopCount(@PathVariable("qid") String qid,
                                                                @PathVariable("count") Integer count) {
        qService.changeQuestionPopCount(count,qid);
        return new CommonResult<>(20000,"OK", "成功！");
    }
}


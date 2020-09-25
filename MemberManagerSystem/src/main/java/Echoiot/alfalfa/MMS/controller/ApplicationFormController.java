package Echoiot.alfalfa.MMS.controller;


import Echoiot.alfalfa.MMS.model.bean.CommonResult;
import Echoiot.alfalfa.MMS.model.bean.PageResult;
import Echoiot.alfalfa.MMS.model.dto.PageParamDTO;
import Echoiot.alfalfa.MMS.model.pojo.ApplicationForm;
import Echoiot.alfalfa.MMS.service.ApplicationFormService;
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
@RequestMapping("/Apf")
public class ApplicationFormController {
    private final ApplicationFormService apfService;

    private final PageParamCheckUtil ppCheck;

    public ApplicationFormController(ApplicationFormService apfService, PageParamCheckUtil ppCheck) {
        this.apfService = apfService;
        this.ppCheck = ppCheck;
    }

    @PostMapping("/addApf")
    public CommonResult<ApplicationForm> addApplicationForm(@RequestBody ApplicationForm apf,
                                                            HttpServletRequest request) {
        apf.setUid((String) request.getAttribute("uid"));
        apfService.addApplicationForm(apf);
        return new CommonResult<>(20000, "OK", apf);
    }

    @PostMapping("/updateApf")
    public CommonResult<ApplicationForm> updateApplicationForm(@RequestBody ApplicationForm apf) {
        apfService.updateApplicationForm(apf);
        return new CommonResult<>(20000, "OK", apf);
    }

    @PostMapping("/findApf")
    public CommonResult<PageResult<ApplicationForm>> findApplicationFormByUid(HttpServletRequest request,
                                                                              @Valid @RequestBody PageParamDTO ppDTO) {
        PageResult<ApplicationForm> apfByUid =
                apfService.findApplicationFormByUid((String) request.getAttribute("uid"), ppDTO);
        return new CommonResult<>(20000, "OK", apfByUid);
    }

    @GetMapping("/findApf/{id}")
    public CommonResult<ApplicationForm> findApplicationFormById(@PathVariable("id") String id,
                                                                 HttpServletRequest request) {
        ApplicationForm apfById = apfService.findApplicationFormById(id, (String) request.getAttribute("uid"));
        return new CommonResult<>(20000,"OK",apfById);
    }

    @PostMapping("/findApfBy/{status}")
    public CommonResult<PageResult<ApplicationForm>> findApplicationFormByUidAndStatus(
            @PathVariable("status") Integer status, @Valid @RequestBody PageParamDTO ppDTO, HttpServletRequest request) {
        PageResult<ApplicationForm> apfByUidAndStatus = apfService.findApplicationFormByUidAndStatus(
                (String) request.getAttribute("id"), status, ppDTO);
        return new CommonResult<>(20000,"OK",apfByUidAndStatus);
    }
}

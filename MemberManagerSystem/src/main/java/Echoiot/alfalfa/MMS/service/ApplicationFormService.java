package Echoiot.alfalfa.MMS.service;


import Echoiot.alfalfa.MMS.dao.ApplicationFormDao;
import Echoiot.alfalfa.MMS.model.bean.PageResult;
import Echoiot.alfalfa.MMS.model.dto.PageParamDTO;
import Echoiot.alfalfa.MMS.model.pojo.ApplicationForm;
import Echoiot.alfalfa.MMS.utils.DateTimeTransferUtil;
import Echoiot.alfalfa.MMS.utils.IdWorker;
import Echoiot.alfalfa.MMS.utils.PageParamCheckUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Alfalfa99
 * @Date 2020/9/15 21:29
 * @Version 1.0
 */
@Service
public class ApplicationFormService {

    private final IdWorker idWorker;
    private final ApplicationFormDao applicationFormDao;
    private final PageParamCheckUtil ppC;

    public ApplicationFormService(IdWorker idWorker, ApplicationFormDao applicationFormDao, PageParamCheckUtil ppC) {
        this.idWorker = idWorker;
        this.applicationFormDao = applicationFormDao;
        this.ppC = ppC;
    }

    /**
     * 新增一条申请表
     *
     * @param applicationForm 申请表实体
     */
    public void addApplicationForm(ApplicationForm applicationForm) {
        applicationForm.setId(idWorker.nextId() + "");
        applicationForm.setAddtime(DateTimeTransferUtil.getNowTimeStamp());
    }

    /**
     * 修改申请表信息（未被审批过的）
     *
     * @param applicationForm 需要更新的申请表
     */
    public void updateApplicationForm(ApplicationForm applicationForm) {
        ApplicationForm targetApf = applicationFormDao.findApplicationFormById(applicationForm.getId());
        if (targetApf.getUid() != applicationForm.getUid()) {
            throw new AccessDeniedException("权限不足");
        } else if (targetApf.getStatus()!=0){
            throw new AccessDeniedException("申请表已被锁定");
        }
        applicationFormDao.updateApplicationForm(applicationForm);
    }

    /**
     * 通过用户id查找申请表
     *
     * @param uid 用户id
     * @return 申请表列表
     */
    public PageResult<ApplicationForm> findApplicationFormByUid(String uid, PageParamDTO ppDTO) {
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<ApplicationForm> applicationFormByUid = applicationFormDao.findApplicationFormByUid(uid);
        return new PageResult<>(page.getPages(),page.getTotal(),applicationFormByUid);
    }
    /**
     * 通过申请表id查找申请表
     *
     * @param id 申请表id
     * @return 申请表实体
     */
    public ApplicationForm findApplicationFormById(String id,String uid) {
        ApplicationForm apfById = applicationFormDao.findApplicationFormById(id);
        if (apfById.getUid() != uid){
            throw new AccessDeniedException("权限不足");
        }
        return apfById;
    }
    /**
     * 通过用户id和申请表状态查找申请表
     *
     * @param id     用户id
     * @param status 申请表状态
     * @return 返回申请表实体
     */
    public PageResult<ApplicationForm> findApplicationFormByUidAndStatus(String id, Integer status,
                                                                        PageParamDTO ppDTO) {
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<ApplicationForm> apfByIdAndStatus = applicationFormDao.findApplicationFormByUidAndStatus(id, status);
        return new PageResult<>(page.getPages(),page.getTotal(),apfByIdAndStatus);
    }

}

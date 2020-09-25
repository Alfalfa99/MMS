package Echoiot.alfalfa.MMS.service;


import Echoiot.alfalfa.MMS.dao.*;
import Echoiot.alfalfa.MMS.model.bean.PageResult;
import Echoiot.alfalfa.MMS.model.dto.PageParamDTO;
import Echoiot.alfalfa.MMS.model.dto.TaskDTO;
import Echoiot.alfalfa.MMS.model.pojo.ApplicationForm;
import Echoiot.alfalfa.MMS.model.pojo.Task;
import Echoiot.alfalfa.MMS.model.pojo.User;
import Echoiot.alfalfa.MMS.utils.DateTimeTransferUtil;
import Echoiot.alfalfa.MMS.utils.IdWorker;
import Echoiot.alfalfa.MMS.utils.PageParamCheckUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Alfalfa99
 * @Date 2020/9/15 21:28
 * @Version 1.0
 */
@Service
@Transactional(rollbackForClassName = "Exception.class")
public class AdminService {
    private final AdminDao adminDao;
    private final IdWorker idWorker;
    private final UserDao userDao;
    private final TaskDao taskDao;
    private final QuestionDao questionDao;
    private final PageParamCheckUtil ppC;
    private final AnswerDao answerDao;

    public AdminService(AdminDao adminDao, IdWorker idWorker, UserDao userDao, TaskDao taskDao,
                        QuestionDao questionDao, PageParamCheckUtil ppC, AnswerDao answerDao) {
        this.adminDao = adminDao;
        this.idWorker = idWorker;
        this.userDao = userDao;
        this.taskDao = taskDao;
        this.questionDao = questionDao;
        this.ppC = ppC;
        this.answerDao = answerDao;
    }

    /**
     * 查询所有用户
     *
     * @return 返回用户列表
     */
    public PageResult<User> findAllUser(PageParamDTO ppDTO) {
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<User> allUser = adminDao.findAllUser();
        return new PageResult<>(page.getPages(), page.getTotal(), allUser);
    }

    /**
     * 通过年级和角色名查询用户
     *
     * @param grade    年级
     * @param roleName 角色名
     * @return 用户列表
     */
    public PageResult<User> findUserByYearAndRole(String grade, String roleName, PageParamDTO ppDTO) {
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<User> userByYearAndRole = adminDao.findUserByYearAndRole(grade, roleName);
        return new PageResult<>(page.getPages(), page.getTotal(), userByYearAndRole);
    }

    /**
     * 通过年级查询用户
     *
     * @param grade 年级
     * @return 用户列表
     */
    public PageResult<User> findUserByYear(String grade, PageParamDTO ppDTO) {
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<User> userByYear = adminDao.findUserByYear(grade);
        return new PageResult<>(page.getPages(), page.getTotal(), userByYear);
    }

    /**
     * 管理员修改用户信息
     *
     * @param user 用户信息
     */
    public void updateUser(User user) {
        adminDao.updateUser(user);
    }


    /**
     * 创建一个任务
     *
     * @param
     */

    public void createTask(TaskDTO taskDTO, String uid) {
        String id = idWorker.nextId() + "";
        String nowTimeStamp = DateTimeTransferUtil.getNowTimeStamp();
        for (String s : taskDTO.getTask_member_id()) {
            Task task = new Task();
            task.setId(id);
            task.setTask_name(taskDTO.getTask_name());
            task.setTask_field(taskDTO.getTask_field());
            task.setTask_start_time(taskDTO.getTask_start_time());
            task.setTask_final_time(taskDTO.getTask_final_time());
            task.setTask_boss_id(uid);
            task.setTask_addtime(nowTimeStamp);
            task.setTask_member_id(s);
            userDao.changeUserTask(1, s);
            adminDao.createTask(task);
        }
    }

    /**
     * 通过任务id 改变任务状态
     *
     * @param status 任务状态
     * @param tid    任务id
     */
    public void setTaskStatus(Integer status, String tid) {
        List<String> memberByTaskId = taskDao.findMemberByTaskId(tid);
        if (status == 0){
            for (String uid : memberByTaskId) {
                userDao.changeUserTask(1,uid);
            }
        } else if (status == 1 || status == 2){
            for (String uid : memberByTaskId) {
                userDao.changeUserTask(-1,uid);
            }
        } else {
            throw new IllegalArgumentException("错误的请求参数");
        }
        adminDao.setTaskStatus(status, tid);
    }

    /**
     * 查找所有申请表
     *
     * @return 申请表列表
     */
    public PageResult<ApplicationForm> findAllApplicationForm(PageParamDTO ppDTO) {
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<ApplicationForm> allApplicationForm = adminDao.findAllApplicationForm();
        return new PageResult<>(page.getPages(), page.getTotal(), allApplicationForm);
    }

    /**
     * 通过申请表状态查找申请表
     *
     * @param status 申请表状态
     * @return
     */
    public PageResult<ApplicationForm> findApplicationFormByStatus(Integer status, PageParamDTO ppDTO) {
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<ApplicationForm> applicationFormByStatus = adminDao.findApplicationFormByStatus(status);
        return new PageResult<>(page.getPages(),page.getTotal(),applicationFormByStatus);
    }


    /**
     * 通过申请表id审批申请表
     *
     * @param status   申请表状态
     * @param admin_id 操作者id
     * @param appId    申请表id
     */
    public void approvalApplication(Integer status, String admin_id, String appId) {
        adminDao.approvalApplication(status, admin_id, appId);
    }

    /**
     * 改变用户角色
     *
     * @param uid      用户id
     * @param roleName 角色名
     */
    public void updateUserRole(String uid, String roleName) {
        if (!roleName.equals("VIP") && !roleName.equals("MEMBER") && !roleName.equals("ADMIN")){
            throw new IllegalArgumentException("错误的参数");
        }
        adminDao.updateUserRole(uid, roleName);
    }

    /**
     * 根据id删除问题（管理员）
     *
     * @param id 问题id
     */
    public void deleteQuestionByAdmin(String id) {
        userDao.changeUserQuestionCount(-1,questionDao.findQuestionById(id).getUid());
        questionDao.deleteQuestionById(id);
    }

    /**
     * 根据id删除一个回复
     *
     * @param id 回复的id
     */
    public void deleteAnswerByAdmin(String id) {
        answerDao.deleteAnswer(id);
    }
}

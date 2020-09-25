package Echoiot.alfalfa.MMS.service;


import Echoiot.alfalfa.MMS.dao.TaskDao;
import Echoiot.alfalfa.MMS.model.bean.PageResult;
import Echoiot.alfalfa.MMS.model.dto.PageParamDTO;
import Echoiot.alfalfa.MMS.model.pojo.Task;
import Echoiot.alfalfa.MMS.utils.PageParamCheckUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Alfalfa99
 * @Date 2020/9/16 17:44
 * @Version 1.0
 */
@Service
@Transactional(rollbackForClassName = "Exception.class")
public class TaskService {

    private final TaskDao taskDao;
    private final PageParamCheckUtil ppC;

    public TaskService(TaskDao taskDao, PageParamCheckUtil ppC) {
        this.taskDao = taskDao;
        this.ppC = ppC;
    }

    /**
     * 找到所有的任务
     *
     * @return 所有任务
     */
    public PageResult<Task> findAllTask(PageParamDTO ppDTO) {
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<Task> allTask = taskDao.findAllTask();
        return new PageResult<>(page.getPages(),page.getTotal(),allTask);
    }

    /**
     * 通过任务id找到任务
     *
     * @param tid 任务id
     * @return 所有任务
     */
    public PageResult<Task> findTaskById(String tid, PageParamDTO ppDTO) {
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<Task> taskById = taskDao.findTaskById(tid);
        return new PageResult<>(page.getPages(),page.getTotal(),taskById);

    }

    /**
     * 通过任务状态查找任务
     *
     * @param status 任务状态
     * @return 返回任务
     */
    public PageResult<Task> findTaskByStatus(Integer status, PageParamDTO ppDTO) {
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<Task> taskByStatus = taskDao.findTaskByStatus(status);
        return new PageResult<>(page.getPages(),page.getTotal(),taskByStatus);

    }

    /**
     * 通过任务发布者找到任务
     *
     * @param bid 任务发布者id
     * @return
     */
    public PageResult<Task> findTaskByBossId(String bid, PageParamDTO ppDTO) {
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<Task> taskByBossId = taskDao.findTaskByBossId(bid);
        return new PageResult<>(page.getPages(),page.getTotal(),taskByBossId);

    }

    /**
     * 通过任务发布者和任务状态找到任务
     *
     * @param bid    任务发布者
     * @param status 任务状态
     * @return
     */
    public PageResult<Task> findTaskByBossIdAndStatus(String bid, Integer status, PageParamDTO ppDTO) {
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<Task> taskByBossIdAndStatus = taskDao.findTaskByBossIdAndStatus(bid, status);
        return new PageResult<>(page.getPages(),page.getTotal(),taskByBossIdAndStatus);

    }

    /**
     * 通过用户id查找任务
     *
     * @param mid 用户id
     * @return
     */
    public PageResult<Task> findTaskByMemberId(String mid, PageParamDTO ppDTO) {
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<Task> taskByMemberId = taskDao.findTaskByMemberId(mid);
        return new PageResult<>(page.getPages(),page.getTotal(),taskByMemberId);

    }

    /**
     * 通过用户id和任务状态查找任务
     *
     * @param mid    用户id
     * @param status 任务状态
     * @return 返回任务列表
     */
    public PageResult<Task> findTaskByMemberIdAndStatus(String mid, Integer status, PageParamDTO ppDTO) {
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<Task> taskByMemberIdAndStatus = taskDao.findTaskByMemberIdAndStatus(mid, status);
        return new PageResult<>(page.getPages(),page.getTotal(),taskByMemberIdAndStatus);

    }


}

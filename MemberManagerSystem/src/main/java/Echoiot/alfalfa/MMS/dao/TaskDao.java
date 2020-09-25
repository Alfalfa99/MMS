package Echoiot.alfalfa.MMS.dao;


import Echoiot.alfalfa.MMS.model.pojo.Task;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Alfalfa99
 * @Date 2020/9/14 20:03
 * @Version 1.0
 */
@Repository
public interface TaskDao {

    /**
     * 找到所有的任务
     *
     * @return 所有任务
     */
    @Select("SELECT * FROM tb_task WHERE 1=1")
    List<Task> findAllTask();

    /**
     * 通过任务id查找执行者
     * @param tid 任务id
     * @return 执行者id列表
     */
    @Select("SELECT task_member_id FROM tb_task WHERE id = #{tid}")
    List<String> findMemberByTaskId(@Param("tid") String tid);
    /**
     * 通过任务id找到任务
     *
     * @param tid 任务id
     * @return 所有任务
     */
    @Select("SELECT * FROM tb_task WHERE id = #{tid}")
    List<Task> findTaskById(@Param("tid") String tid);

    /**
     * 通过任务状态查找任务
     *
     * @param status 任务状态
     * @return 返回任务
     */
    @Select("SELECT * FROM tb_task WHERE  task_status = #{status}")
    List<Task> findTaskByStatus(@Param("status") Integer status);

    /**
     * 通过任务发布者找到任务
     *
     * @param bid 任务发布者id
     * @return
     */
    @Select("SELECT * FROM tb_task WHERE task_boss_id = #{bid}")
    List<Task> findTaskByBossId(@Param("bid") String bid);

    /**
     * 通过任务发布者和任务状态找到任务
     *
     * @param bid    任务发布者
     * @param status 任务状态
     * @return
     */
    @Select("SELECT * FROM tb_task WHERE task_boss_id = #{bid} AND task_status = #{status}")
    List<Task> findTaskByBossIdAndStatus(@Param("bid") String bid, @Param("status") Integer status);

    /**
     * 通过用户id查找任务
     *
     * @param mid 用户id
     * @return
     */
    @Select("SELECT * FROM tb_task WHERE task_member_id = #{mid} ")
    List<Task> findTaskByMemberId(@Param("mid") String mid);

    /**
     * 通过用户id和任务状态查找任务
     *
     * @param mid    用户id
     * @param status 任务状态
     * @return 返回任务列表
     */
    @Select("SELECT * FROM tb_task WHERE task_member_id = #{mid} AND task_status = #{status}")
    List<Task> findTaskByMemberIdAndStatus(@Param("mid") String mid, @Param("status") Integer status);

    /**
     * 通过 任务id删除 任务
     *
     * @param tid 任务id
     */
    @Delete("DELETE FROM tb_task WHERE id = #{tid}")
    void deleteTask(@Param("tid") String tid);
}
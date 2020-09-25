package Echoiot.alfalfa.MMS.dao;


import Echoiot.alfalfa.MMS.model.pojo.ApplicationForm;
import Echoiot.alfalfa.MMS.model.pojo.Task;
import Echoiot.alfalfa.MMS.model.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Alfalfa99
 * @Date 2020/9/14 17:33
 * @Version 1.0
 */
@Repository
public interface AdminDao {

    /**
     * 查询所有用户
     *
     * @return
     */
    @Select("SELECT * FROM tb_user")
    List<User> findAllUser();

    /**
     * 通过年级和角色名查询用户
     *
     * @param grade
     * @param roleName
     * @return
     */
    @Select("SELECT * FROM tb_user tu, tb_role tr WHERE tu.grade = #{grade}  AND tr.rolename = #{rolename} AND " +
            "tu.id = tr.uid")
    List<User> findUserByYearAndRole(@Param("grade") String grade, @Param("rolename") String roleName);

    /**
     * 通过年级查询用户
     *
     * @param grade
     * @return
     */
    @Select("SELECT * FROM tb_user WHERE grade = #{grade}")
    List<User> findUserByYear(@Param("grade") String grade);

    /**
     * 管理员修改用户信息
     *
     * @param user 用户实体
     */
    @Update("UPDATE tb_user SET username = #{user.username}, password = #{user.password}, email = #{user.nickname}," +
            "nickname = #{user.nickname}, phone = #{user.phone}, age = #{user.age}, gender = #{user.gender}," +
            "college = #{user.college}, grade = #{user.grade}, classes = #{user.classes}, enable = #{user.enable}" +
            "WHERE id = #{user.id}")
    void updateUser(@Param("user") User user);


    /**
     * 创建一个任务
     *
     * @param task
     */
    @Insert("INSERT INTO tb_task(id,task_boss_id,task_member_id,task_name,task_field,task_start_time,task_final_time," +
            "task_addtime) VALUES(#{task.id},#{task.task_boss_id},#{task.task_member_id}," +
            "#{task.task_name},#{task.task_field},#{task.task_start_time},#{task.task_final_time}," +
            "#{task.task_addtime})")
    void createTask(@Param("task") Task task);

    /**
     * 通过任务id 改变任务状态
     *
     * @param status 任务状态
     * @param tid    任务id
     */
    @Update("UPDATE tb_task SET task_status = #{task_status} WHERE id = #{tid}")
    void setTaskStatus(@Param("task_status") Integer status, @Param("tid") String tid);

    /**
     * 查找所有申请表
     *
     * @return
     */
    @Select("SELECT * FROM tb_aapplicationform WHERE 1=1")
    List<ApplicationForm> findAllApplicationForm();

    /**
     * 通过申请表状态查找申请表
     *
     * @param status 申请表状态
     * @return
     */
    @Select("SELECT * FROM tb_applicationform WHERE status = #{status}")
    List<ApplicationForm> findApplicationFormByStatus(@Param("status") Integer status);


    /**
     * 通过申请表id审批申请表
     *
     * @param status   申请表状态
     * @param admin_id 操作者id
     * @param appId    申请表id
     */
    @Update("UPDATE tb_applicationform SET status = #{status} , admin_id = #{admin_id} WHERE id = #{appid}")
    void approvalApplication(@Param("status") Integer status, @Param("admin_id") String admin_id,
                             @Param("appid") String appId);

    /**
     * 改变用户角色
     * @param uid 用户id
     * @param roleName 角色名
     */
    @Update("UPDATE tb_role SET roleName = #{rolename} WHERE uid = #{uid}")
    void updateUserRole(@Param("uid") String uid, @Param("rolename") String roleName);
}

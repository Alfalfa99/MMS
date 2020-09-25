package Echoiot.alfalfa.MMS.dao;


import Echoiot.alfalfa.MMS.model.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @Author Alfalfa99
 * @Date 2020/9/13 14:36
 * @Version 1.0
 * 用户Dao
 */
@Repository
public interface UserDao {

    /**
     * 注册用户
     * 这里应该分成三种接口 1：通过用户名-密码注册， 2：通过邮箱-密码（验证码）注册，3：通过手机号-密码（验证码）注册
     *
     * @param user 用户实体
     */
    @Insert("INSERT INTO tb_user(id,username,password,nickname,enable,addtime)" +
            "VALUES(#{user.id},#{user.username},#{user.password},#{user.nickname},#{user.enable},#{user.addtime})")
    void userRegister(@Param("user") User user);

    /**
     * 用户注册时分配用户角色
     *
     * @param uid 用户id
     */
    @Insert("INSERT INTO tb_role(uid,rolename) VALUES(#{uid},default)")
    void allocateUserRole(@Param("uid") String uid);


    /**
     * 通过用户id查询用户角色
     *
     * @param id 用户id
     * @return 用户角色
     */
    @Select("SELECT rolename FROM tb_role WHERE uid = #{id}")
    String getUserRole(@Param("id") String id);

    /**
     * 通过登录的账号查询是否存在该用户
     *
     * @param loginName 登录名，可能是username也可能是email
     * @return 返回用户实体
     */
    @Select("SELECT * FROM tb_user WHERE (username = #{loginName} OR email = #{loginName})")
    User userLogin(@Param("loginName") String loginName);

    /**
     * 通过用户id查询用户详情
     *
     * @param id 用户id
     * @return 用户实体
     */
    @Select("SELECT * FROM tb_user WHERE id = #{id}")
    User getUserDetailById(@Param("id") String id);

    /**
     * 用户修改用户非敏感信息
     *
     * @param user 用户实体
     */
    @Update("UPDATE tb_user SET nickname = #{user.nickname}, age = #{user.age}, email = #{user.email}, " +
            "phone = #{user.phone},gender = #{user.gender}, college = #{user.college}, grade = #{user.grade}," +
            "classes = #{user.classes} WHERE id = #{user.id}")
    void updateUserDetail(@Param("user") User user);

    /**
     * 用户修改密码
     * @param password 新密码
     * @param uid 用户id
     */
    @Update("UPDATE tb_user SET password = #{password} WHERE id = #{uid}")
    void updateUserPwd(@Param("password") String password, @Param("uid") String uid);

    /**
     * 通过uid完成用户任务数的增加和减少
     *
     * @param count +1、-1
     * @param id    用户id
     */
    @Update("UPDATE tb_user SET task_count = task_count + #{count} WHERE id = #{id}")
    void changeUserTask(@Param("count") Integer count, @Param("id") String id);

    /**
     * 通过uid完成用户点赞数的增加和减少
     *
     * @param count +1、-1
     * @param id    用户id
     */
    @Update("UPDATE tb_user SET popcount = popcount + #{count} WHERE id = #{id}")
    void changeUserPop(@Param("count") Integer count, @Param("id") String id);

    /**
     * 通过uid完成用户问题数的增加和减少
     *
     * @param count +1、-1
     * @param id    用户id
     */
    @Update("UPDATE tb_user SET question_count = question_count + #{count} WHERE id = #{id}")
    void changeUserQuestionCount(@Param("count") Integer count, @Param("id") String id);

    /**
     * 通过uid完成用户回答数的增加和减少
     *
     * @param count +1、-1
     * @param id    用户id
     */
    @Update("UPDATE tb_user SET answer_count = answer_count + #{count} WHERE id = #{id}")
    void changeUserAnswerCount(@Param("count") Integer count, @Param("id") String id);

}

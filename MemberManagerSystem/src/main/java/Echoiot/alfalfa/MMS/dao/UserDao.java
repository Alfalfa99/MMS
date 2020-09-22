package Echoiot.alfalfa.MMS.dao;

import Echoiot.alfalfa.MMS.model.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author Alfalfa99
 * @Date 2020/9/22 15:19
 * @Version 1.0
 */

@Repository
public interface UserDao {
    /**
     * 注册用户
     *
     * @param user 用户pojo
     */
    @Insert("INSERT INTO tb_user(id,username,password,nickname,enable,addtime)" +
            "VALUES(#{user.id},#{user.username},#{user.password},#{user.nickname},#{user.enable},#{user.addtime})")
    void userRegister(@Param("user") User user);

    /**
     * 通过登录的账号查询是否存在该用户
     *
     * @param loginName 登录名，可能是username也可能是email
     * @return 返回用户密码
     */
    @Select("SELECT password FROM tb_user WHERE username = #{loginName}")
    User userLogin(@Param("loginName") String loginName);
}
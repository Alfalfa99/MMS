package Echoiot.alfalfa.MMS.dao;


import Echoiot.alfalfa.MMS.model.pojo.ApplicationForm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Alfalfa99
 * @Date 2020/9/14 19:26
 * @Version 1.0
 */
@Repository
public interface ApplicationFormDao {
    /**
     * 新增一条申请表
     *
     * @param applicationForm 申请表实体
     */
    @Insert("INSERT INTO tb_applicationform(id,uid,name,email,phone,gender,age,college,grade,classes,selfintroduce," +
            "preexp,addtime,status,otherLab,admin_id) VALUES(#{apf.id},#{apf.uid},#{apf.name},#{apf.email}," +
            "#{apf.phone},#{apf.gender},#{apf.age},#{apf.college},#{apf.grade},#{apf.classes},#{apf.selfintroduce}," +
            "#{apf.preexp},#{apf.addtime},#{apf.status},#{apf.otherLab},#{apf.admin_id})")
    void addApplicationForm(@Param("apf") ApplicationForm applicationForm);

    /**
     * 修改申请表信息（未被审批过的）
     *
     * @param applicationForm 需要更新的申请表
     */
    @Update("UPDATE tb_applicationform SET name = #{apf.name}, email = #{apf.email}, phone = #{apf.phone}," +
            "gender = #{apf.gender}, age = #{apf.age}, college = #{apf.college}, grade = #{apf.grade}," +
            "classes = #{apf.classes}, selfintroduce = #{apf.selfintroduce}, preexp = #{apf.preexp}")
    void updateApplicationForm(@Param("apf") ApplicationForm applicationForm);

    /**
     * 通过用户id查找申请表
     *
     * @param uid 用户id
     * @return 申请表列表
     */
    @Select("SELECT * FROM tb_applicationform WHERE uid = #{uid}")
    List<ApplicationForm> findApplicationFormByUid(@Param("uid") String uid);

    /**
     * 通过申请表id查找申请表
     * @param id 申请表id
     * @return 申请表实体
     */
    @Select("SELECT * FROM tb_applicationform WHERE id = #{id}")
    ApplicationForm findApplicationFormById(@Param("id") String id);

    /**
     * 通过用户id和申请表状态查找申请表
     * @param id 用户id
     * @param status 申请表状态
     * @return 返回申请表实体
     */
    @Select("SELECT * FROM tb_applicationform WHERE uid = #{id} AND status=#{status}")
    List<ApplicationForm> findApplicationFormByUidAndStatus(@Param("id") String id,@Param("status") Integer status);

}

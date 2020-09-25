package Echoiot.alfalfa.MMS.dao;


import Echoiot.alfalfa.MMS.model.pojo.Answer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Alfalfa99
 * @Date 2020/9/15 12:56
 * @Version 1.0
 */
@Repository
public interface AnswerDao {
    /**
     * 发起一个回复
     *
     * @param as 回复实体
     */
    @Insert("INSERT INTO tb_answer(id,uid,question_id,parentid,answer_field,addtime),VALUE(#{as.id},#{as.uid}," +
            "#{as.question_id},#{as.parentid},#{as.answer_field},#{as.addtime}")
    void addAnswer(@Param("as") Answer as);

    /**
     * 根据id删除一个回复
     *
     * @param id 回复的id
     */
    @Delete("DELETE FROM tb_answer WHERE id = #{id}")
    void deleteAnswer(@Param("id") String id);

    /**
     * 通过问题的id找到该问题下所有回复
     * @param qid 问题id
     * @return 回复列表
     */
    @Select("SELECT * FROM tb_answer WHERE question_id = #{qid}")
    List<Answer> findAllAnswerByQid(@Param("qid") String qid);


    /**
     * 通过用户id找到全部回复
     * @param uid 用户id
     * @return 回复列表
     */
    @Select("SELECT * FROM tb_answer WHERE uid = #{uid}")
    List<Answer> findAllAnswerByUid(@Param("uid") String uid);

    /**
     * 通过回复的id找到该回复
     * @param id 问题id
     * @return 回复列表
     */
    @Select("SELECT * FROM tb_answer WHERE id = #{id}")
    Answer findAnswerById(@Param("id") String id);

    /**
     * 通过id完成回答点赞的增加和减少
     *
     * @param count +1、-1
     * @param id    回答id
     */
    @Update("UPDATE tb_answer SET popcount = popcount + #{count} WHERE id = #{id}")
    void changeAnswerPopCount(@Param("count") Integer count, @Param("id") String id);

}

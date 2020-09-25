package Echoiot.alfalfa.MMS.dao;


import Echoiot.alfalfa.MMS.model.pojo.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Alfalfa99
 * @Date 2020/9/15 12:56
 * @Version 1.0
 */
@Repository
public interface QuestionDao {


    /**
     * 增加一个问题
     * @param question 问题实体
     */
    @Insert("INSERT INTO tb_question(id,uid,question_title,question_field,addtime,lastupdatetime) VALUE " +
            "(#{qs.id},#{qs.uid},#{qs.question_title},#{qs.question_field},#{qs.addtime},#{qs.lastupdatetime})")
    void addQuestion(@Param("qs") Question question);

    /**
     * 通过问题id删除问题
     * @param id 问题id
     */
    @Delete("DELETE FROM tb_question WHERE id = #{id}")
    void deleteQuestionById(@Param("id") String id);

    /**
     * 找到所有问题
     * @return 问题列表
     */
    @Select("SELECT * FROM tb_question WHERE 1=1")
    List<Question> findAllQuestion();

    /**
     * 通过问题id找到问题
     * @param id 问题id
     * @return 问题实体
     */
    @Select("SELECT * FROM tb_question WHERE id = #{id}")
    Question findQuestionById(@Param("id") String id);


    /**
     * 在某用户删除一个问题之前判断问题是不是该用户提的
     * @param id 问题id
     * @param uid 用户id
     * @return 判断某问题是否为用户的提问
     */
    @Select("SELECT * FROM tb_question WHERE id = #{id} AND uid = #{uid}")
    Integer findQuestionByIdAndUid(@Param("id") String id,@Param("uid") String uid);

    /**
     * 通过用户id找到用户提问的所有问题
     * @param uid 用户id
     * @return 问题列表
     */
    @Select("SELECT * FROM tb_question WHERE uid = #{uid}")
    List<Question> findQuestionByUid(@Param("uid") String uid);

    /**
     * 通过标题模糊搜索所有问题
     * @param title 标题
     * @return 问题列表
     */
    @Select("SELECT * FROM tb_question WHERE question_title LIKE concat('%', #{title},'%')")
    List<Question> findQuestionByTitle(@Param("title") String title);

    /**
     * 通过id完成问题的回答数的增加和减少
     *
     * @param count +1、-1
     * @param id    问题id
     */
    @Update("UPDATE tb_question SET popcount = popcount + #{count} WHERE id = #{id}")
    void changeQuestionAnswerCount(@Param("count") Integer count, @Param("id") String id);

    /**
     * 通过id完成问题点赞数的增加和减少
     *
     * @param count +1、-1
     * @param id    问题id
     */
    @Update("UPDATE tb_question SET answer_count = answer_count + #{count} WHERE id = #{id}")
    void changeQuestionPopCount(@Param("count") Integer count, @Param("id") String id);
}

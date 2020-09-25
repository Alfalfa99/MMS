package Echoiot.alfalfa.MMS.service;


import Echoiot.alfalfa.MMS.dao.QuestionDao;
import Echoiot.alfalfa.MMS.dao.UserDao;
import Echoiot.alfalfa.MMS.model.bean.PageResult;
import Echoiot.alfalfa.MMS.model.dto.PageParamDTO;
import Echoiot.alfalfa.MMS.model.pojo.Question;
import Echoiot.alfalfa.MMS.utils.DateTimeTransferUtil;
import Echoiot.alfalfa.MMS.utils.IdWorker;
import Echoiot.alfalfa.MMS.utils.PageParamCheckUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Alfalfa99
 * @Date 2020/9/15 21:28
 * @Version 1.0
 */
@Service
@Transactional(rollbackForClassName = "exception.class")
public class QuestionService {

    private final IdWorker idWorker;
    private final QuestionDao questionDao;
    private final UserDao userDao;
    private final PageParamCheckUtil ppC;

    public QuestionService(IdWorker idWorker, QuestionDao questionDao, UserDao userDao, PageParamCheckUtil ppC) {
        this.idWorker = idWorker;
        this.questionDao = questionDao;
        this.userDao = userDao;
        this.ppC = ppC;
    }

    /**
     * 增加一个问题
     *
     * @param question 问题实体
     */
    public void addQuestion(Question question) {
        question.setId(idWorker.nextId() + "");
        question.setAddtime(DateTimeTransferUtil.getNowTimeStamp());
        questionDao.addQuestion(question);
        userDao.changeUserQuestionCount(1, question.getUid());
    }

    /**
     * 根据id删除问题（用户版）
     *
     * @param id  问题id
     * @param uid 用户id
     */
    public void deleteQuestionByUser(String id, String uid) {
        if (questionDao.findQuestionByIdAndUid(id, uid) != 0) {
            questionDao.deleteQuestionById(id);
            userDao.changeUserQuestionCount(-1, uid);
        } else {
            throw new AccessDeniedException("权限不足");
        }
    }

    public PageResult<Question> findAllQuestion(PageParamDTO ppDTO) {
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<Question> allQuestion = questionDao.findAllQuestion();
        return new PageResult<>(page.getPages(),page.getTotal(),allQuestion);
    }

    /**
     * 通过问题id找到问题
     *
     * @param id 问题id
     * @return 问题实体
     */
    public Question findQuestionById(String id) {
        return questionDao.findQuestionById(id);
    }

    /**
     * 通过用户id找到用户提问的所有问题
     *
     * @param uid 用户id
     * @return 问题列表
     */
    public PageResult<Question> findQuestionByUid(String uid, PageParamDTO ppDTO) {
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<Question> questionByUid = questionDao.findQuestionByUid(uid);
        return new PageResult<>(page.getPages(),page.getTotal(),questionByUid);
    }

    /**
     * 通过标题模糊搜索所有问题
     *
     * @param title 标题
     * @return 问题列表
     */
    public PageResult<Question> findQuestionByTitle(String title, PageParamDTO ppDTO) {
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<Question> questionByTitle = questionDao.findQuestionByTitle(title);
        return new PageResult<>(page.getPages(),page.getTotal(),questionByTitle);
    }


    /**
     * 通过id完成用户点赞的增加和减少
     *
     * @param count +1、-1
     * @param id    问题id
     */
    public void changeQuestionPopCount(Integer count, String id) {
        Question questionById = questionDao.findQuestionById(id);
        questionDao.changeQuestionPopCount(count, id);
        userDao.changeUserPop(count, questionById.getUid());
    }




}

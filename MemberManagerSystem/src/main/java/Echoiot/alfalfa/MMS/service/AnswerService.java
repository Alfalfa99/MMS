package Echoiot.alfalfa.MMS.service;


import Echoiot.alfalfa.MMS.dao.AnswerDao;
import Echoiot.alfalfa.MMS.dao.QuestionDao;
import Echoiot.alfalfa.MMS.dao.UserDao;
import Echoiot.alfalfa.MMS.model.bean.PageResult;
import Echoiot.alfalfa.MMS.model.dto.PageParamDTO;
import Echoiot.alfalfa.MMS.model.pojo.Answer;
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
@Transactional(rollbackForClassName = "Exception.class")
public class AnswerService {
    private final IdWorker idWorker;
    private final AnswerDao answerDao;
    private final QuestionDao questionDao;
    private final UserDao userDao;
    private final PageParamCheckUtil ppC;

    public AnswerService(IdWorker idWorker, AnswerDao answerDao, QuestionDao questionDao, UserDao userDao, PageParamCheckUtil ppC) {
        this.idWorker = idWorker;
        this.answerDao = answerDao;
        this.questionDao = questionDao;
        this.userDao = userDao;
        this.ppC = ppC;
    }

    /**
     * 发起一个回复
     *
     * @param as 回复实体
     */
    public void addAnswer(Answer as) {
        as.setId(idWorker.nextId() + "");
        as.setAddtime(DateTimeTransferUtil.getNowTimeStamp());
        answerDao.addAnswer(as);
        questionDao.changeQuestionAnswerCount(1, as.getQuestion_id());
        userDao.changeUserAnswerCount(1, as.getUid());
    }



    /**
     * 根据id删除一个回复
     *
     * @param id 回复的id
     */
    public void deleteAnswerByUser(String id, String uid) {
        Answer targetAnswer = answerDao.findAnswerById(id);
        if (targetAnswer != null && targetAnswer.getUid() == uid) {
            answerDao.deleteAnswer(id);
            questionDao.changeQuestionAnswerCount(-1, targetAnswer.getQuestion_id());
            userDao.changeUserAnswerCount(-1, targetAnswer.getUid());
        } else {
            throw new AccessDeniedException("权限不足");
        }
    }


    /**
     * 通过问题的id找到该问题下所有回复
     *
     * @param qid 问题id
     * @return 回复列表
     */
    public PageResult<Answer> findAllAnswerByQid(String qid, PageParamDTO ppDTO) {
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<Answer> allAnswerByQid = answerDao.findAllAnswerByQid(qid);
        return new PageResult<>(page.getPages(),page.getTotal(),allAnswerByQid);
    }

    /**
     * 通过用户id找到用户的全部回复
     * @param uid 用户id
     * @return 回复列表
     */
    public PageResult<Answer> findAllAnswerByUid(String uid, PageParamDTO ppDTO){
        Page<Object> page = PageHelper.startPage(ppDTO.getCp(), ppDTO.getPs(), ppC.CheckOrder(ppDTO.getOrder()));
        List<Answer> allAnswerByUid = answerDao.findAllAnswerByUid(uid);
        return new PageResult<>(page.getPages(),page.getTotal(),allAnswerByUid);
    }




    /**
     * 通过id完成回答点赞数的增加和减少
     *
     * @param count +1、-1
     * @param id    回答id
     */
    public void changeAnswerPopCount(Integer count, String id) {
        answerDao.changeAnswerPopCount(count, id);
        userDao.changeUserPop(count, answerDao.findAnswerById(id).getUid());
    }
}

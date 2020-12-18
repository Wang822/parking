package com.backend.shop.service.Impl;

import com.backend.shop.mapper.QuestionPostReplyDao;
import com.backend.shop.pojo.QuestionPostReply;
import com.backend.shop.service.QuestionPostReplyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionPostReplyServiceImpl implements QuestionPostReplyService {

    @Autowired
    private QuestionPostReplyDao questionPostReplyDao;

    @Override
    public void addQuestionPostReply(QuestionPostReply questionPostReply){
        questionPostReplyDao.add(questionPostReply);
    }

    @Override
    public List<QuestionPostReply> selectQuestionPostReplies(int qPostId){
        return questionPostReplyDao.selectReplies(qPostId);
    }

    @Override
    public void deleteQuestionPostReplies(int qPostId){
        questionPostReplyDao.delete(qPostId);
    }

    @Override
    public String selectNickname(int qReplyId){
        return questionPostReplyDao.findNickname(qReplyId);
    }

    @Override
    public void deleteOneQuestionPostReply(int qReplyId){
        questionPostReplyDao.deleteOneReply(qReplyId);
    }

}

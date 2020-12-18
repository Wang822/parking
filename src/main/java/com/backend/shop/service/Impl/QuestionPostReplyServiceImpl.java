package com.backend.shop.service.Impl;

import com.backend.shop.mapper.QuestionPostReplyDao;
import com.backend.shop.pojo.QuestionPostReply;
import com.backend.shop.service.QuestionPostReplyService;
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

}

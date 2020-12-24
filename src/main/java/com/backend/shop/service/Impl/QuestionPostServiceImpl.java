package com.backend.shop.service.Impl;

import com.backend.shop.mapper.QuestionPostDao;
import com.backend.shop.pojo.QuestionPost;
import com.backend.shop.service.QuestionPostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionPostServiceImpl implements QuestionPostService {

    @Autowired
    private QuestionPostDao questionPostDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<QuestionPost> findAll(){
        return questionPostDao.findAll();
    }

    @Override
    public void addQuestionPost(QuestionPost questionPost){
        questionPostDao.add(questionPost);
    }

    @Override
    public void deleteQuestionPost(int qPostId){questionPostDao.delete(qPostId);}

    @Override
    public String selectNickname(int qPostId){

        return questionPostDao.findNickname(qPostId);
    }

    @Override
    public void deleteReplies(int qPostId){
        questionPostDao.deleteRe(qPostId);
    }


    @Override
    public int selectRCount(int qPostId){
        return questionPostDao.selectCount(qPostId);
    }


    @Override
    public List<QuestionPost> findAllById(int userId){
        return questionPostDao.findAllById(userId);
    }

    @Override
    public int findPostUserId(int qPostId){
        return questionPostDao.findPostUserId(qPostId);
    }

}

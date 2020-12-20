package com.backend.shop.service.Impl;

import com.backend.shop.mapper.AskForGoodPostDao;
import com.backend.shop.pojo.AskForGoodPost;
import com.backend.shop.service.AskForGoodPostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AskForGoodPostServiceImpl implements AskForGoodPostService {

    @Autowired
    private AskForGoodPostDao askForGoodPostDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<AskForGoodPost> findAll(){
        return askForGoodPostDao.findAll();
    }

    @Override
    public void addAskForGoodPost(AskForGoodPost askForGoodPost){
        askForGoodPostDao.add(askForGoodPost);
    }

    @Override
    public void deleteAskForGoodPost(int afgPostId){askForGoodPostDao.delete(afgPostId);}

    @Override
    public String selectNickname(int afgPostId){
        return askForGoodPostDao.findNickname(afgPostId);
    }

    @Override
    public int selectRCount(int afgPostId){
        return askForGoodPostDao.selectCount(afgPostId);
    }

    @Override
    public void deleteReplies(int afgPostId){
        askForGoodPostDao.deleteRe(afgPostId);
    }

    @Override
    public List<AskForGoodPost> findAllById(int userId){
        return askForGoodPostDao.findAllById(userId);
    }

}

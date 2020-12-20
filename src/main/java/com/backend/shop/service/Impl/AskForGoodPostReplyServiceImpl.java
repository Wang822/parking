package com.backend.shop.service.Impl;

import com.backend.shop.mapper.AskForGoodPostReplyDao;
import com.backend.shop.pojo.AskForGoodPostReply;
import com.backend.shop.service.AskForGoodPostReplyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AskForGoodPostReplyServiceImpl implements AskForGoodPostReplyService {
    @Autowired
    private AskForGoodPostReplyDao askForGoodPostReplyDao;

    @Override
    public void addAskForGoodPostReply(AskForGoodPostReply askForGoodPostReply){
        askForGoodPostReplyDao.add(askForGoodPostReply);
    }

    @Override
    public List<AskForGoodPostReply> selectAskForGoodPostReplies(int afgPostId){
        return askForGoodPostReplyDao.selectReplies(afgPostId);
    }

    @Override
    public void deleteAskForGoodPostReplies(int afgPostId){
        askForGoodPostReplyDao.delete(afgPostId);
    }

    @Override
    public String selectNickname(int afgReplyId){
        return askForGoodPostReplyDao.findNickname(afgReplyId);
    }

    @Override
    public void deleteOneAskForGoodPostReply(int afgReplyId){
        askForGoodPostReplyDao.deleteOneReply(afgReplyId);
    }

}

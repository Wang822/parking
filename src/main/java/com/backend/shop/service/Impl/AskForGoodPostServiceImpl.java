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


}

package com.backend.shop.service.Impl;

import com.backend.shop.mapper.RecommendGoodsPostDao;
import com.backend.shop.pojo.RecommendGoodsPost;
import com.backend.shop.service.RecommendGoodsPostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RecommendGoodsPostServiceImpl implements RecommendGoodsPostService {


    @Autowired
    private RecommendGoodsPostDao recommendGoodsPostDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RecommendGoodsPost> findAll(){
        return recommendGoodsPostDao.findAll();
    }

    @Override
    public void addRecommendGoodsPost(RecommendGoodsPost recommendGoodsPost){
        recommendGoodsPostDao.add(recommendGoodsPost);
    }

    @Override
    public void deleteRecommendGoodsPost(int rgPostId){recommendGoodsPostDao.delete(rgPostId);}


}

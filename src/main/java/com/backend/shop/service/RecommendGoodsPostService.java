package com.backend.shop.service;

import com.backend.shop.pojo.RecommendGoodsPost;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface RecommendGoodsPostService {

    List<RecommendGoodsPost> findAll();

    void addRecommendGoodsPost(RecommendGoodsPost recommendGoodsPost);

    void deleteRecommendGoodsPost(int rgPostId);

    String selectNickname(int rgPostId);
}

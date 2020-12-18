package com.backend.shop.mapper;

import com.backend.shop.pojo.RecommendGoodsPost;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RecommendGoodsPostDao {

    List<RecommendGoodsPost> findAll();
    void add(RecommendGoodsPost recommendGoodsPost);
    void delete(@Param("rgPostId") int rgPostId);
}

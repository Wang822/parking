package com.backend.shop.mapper;

import com.backend.shop.pojo.AskForGoodPost;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AskForGoodPostDao {

    List<AskForGoodPost> findAll();
    void add(AskForGoodPost askForGoodPost);



}

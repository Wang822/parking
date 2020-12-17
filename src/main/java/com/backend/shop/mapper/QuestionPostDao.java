package com.backend.shop.mapper;

import com.backend.shop.pojo.QuestionPost;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionPostDao {

    List<QuestionPost> findAll();
    void add(QuestionPost questionPost);

}

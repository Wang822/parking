package com.backend.shop.mapper;

import com.backend.shop.pojo.QuestionPostReply;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionPostReplyDao {
    void add(QuestionPostReply questionPostReply);
}

package com.backend.shop.mapper;

import com.backend.shop.pojo.QuestionPost;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QuestionPostDao {

    List<QuestionPost> findAll();
    void add(QuestionPost questionPost);
    void delete(@Param("qPostId") int qPostId);

    String findNickname(@Param("qPostId") int qPostId);
    void deleteRe(@Param("qPostId") int qPostId);


    int selectCount(@Param("qPostId") int qPostId);

    List<QuestionPost> findAllById(@Param("userId") int userId);

    int findPostUserId(@Param("qPostId") int qPostId);



}

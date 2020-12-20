package com.backend.shop.mapper;

import com.backend.shop.pojo.QuestionPost;
import com.backend.shop.pojo.ReplyList;
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

    String findContent(@Param("qPostId") int qPostId);
    int findAnswerid(@Param("qPostId") int qPostId);
    List<ReplyList> findReplies(@Param("userId") int userId);
    int selectCount(@Param("qPostId") int qPostId);



}

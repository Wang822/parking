package com.backend.shop.mapper;

import com.backend.shop.pojo.AskForGoodPostReply;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AskForGoodPostReplyDao {

    void add(AskForGoodPostReply askForGoodPostReply);
    List<AskForGoodPostReply> selectReplies(int afgPostId);
    void delete(@Param("afgPostId") int afgPostId);

    String findNickname(@Param("afgReplyId") int afgReplyId);
    void deleteOneReply(@Param("afgReplyId") int afgReplyId);
    String findAvatar(@Param("afgReplyId") int afgReplyId);

}

package com.backend.shop.mapper;

import com.backend.shop.pojo.QuestionPostReply;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QuestionPostReplyDao {
    void add(QuestionPostReply questionPostReply);
    List<QuestionPostReply> selectReplies(int qPostId);
    void delete(@Param("qPostId") int qPostId);

    String findNickname(@Param("qReplyId") int qReplyId);
    void deleteOneReply(@Param("qReplyId") int qReplyId);

    int findReplyUserId(@Param("qReplyId") int qReplyId);

}

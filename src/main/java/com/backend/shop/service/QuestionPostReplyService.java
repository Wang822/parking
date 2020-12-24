package com.backend.shop.service;

import com.backend.shop.pojo.QuestionPostReply;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface QuestionPostReplyService {

    void addQuestionPostReply(QuestionPostReply questionPostReply);
    List<QuestionPostReply> selectQuestionPostReplies(int qPostId);
    void deleteQuestionPostReplies(int qPostId);
    String selectNickname(int qReplyId);

    void deleteOneQuestionPostReply(int qReplyId);

    int findReplyUserId(int qReplyId);

}

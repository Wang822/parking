package com.backend.shop.service;

import com.backend.shop.pojo.AskForGoodPostReply;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface AskForGoodPostReplyService {
    void addAskForGoodPostReply(AskForGoodPostReply askForGoodPostReply);
    List<AskForGoodPostReply> selectAskForGoodPostReplies(int afgPostId);
    void deleteAskForGoodPostReplies(int afgPostId);
    String selectNickname(int afgReplyId);
    String selectAvatar(int afgReplyId);
    void deleteOneAskForGoodPostReply(int afgReplyId);

    int findReplyUserId(int afgReplyId);

}

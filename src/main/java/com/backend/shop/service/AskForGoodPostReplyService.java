package com.backend.shop.service;

import com.backend.shop.pojo.AskForGoodPostReply;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface AskForGoodPostReplyService {
    void addAskForGoodPostReply(AskForGoodPostReply askForGoodPostReply);
    List<AskForGoodPostReply> selectAskForGoodPostReplies(int afgPostId);
    void deleteAskForGoodPostReplies(int afgPostId);
    String selectNickname(int afgReplyId);

    void deleteOneAskForGoodPostReply(int afgReplyId);
}

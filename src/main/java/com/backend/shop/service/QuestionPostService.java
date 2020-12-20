package com.backend.shop.service;

import com.backend.shop.pojo.QuestionPost;
import com.backend.shop.pojo.ReplyList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface QuestionPostService {

    List<QuestionPost> findAll();
    void addQuestionPost(QuestionPost questionPost);
    void deleteQuestionPost(int qPostId);
    String selectNickname(int qPostId);
    void deleteReplies(int qPostId);
    String findContent(int qPostId);
    int findAnswerid(int qPostId);
    List<ReplyList> findQReplies(int userId);
    int selectRCount(int qPostId);
}

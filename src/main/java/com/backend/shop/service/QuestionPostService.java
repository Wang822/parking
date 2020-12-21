package com.backend.shop.service;

import com.backend.shop.pojo.QuestionPost;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface QuestionPostService {

    List<QuestionPost> findAll();
    void addQuestionPost(QuestionPost questionPost);
    void deleteQuestionPost(int qPostId);
    String selectNickname(int qPostId);
    void deleteReplies(int qPostId);

    int selectRCount(int qPostId);
    List<QuestionPost> findAllById(int userId);
}

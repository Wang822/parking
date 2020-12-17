package com.backend.shop.service;

import com.backend.shop.pojo.QuestionPost;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface QuestionPostService {

    List<QuestionPost> findAll();
    void addQuestionPost(QuestionPost questionPost);
}
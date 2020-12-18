package com.backend.shop.service;

import com.backend.shop.pojo.QuestionPostReply;
import org.springframework.stereotype.Service;

@Service
public interface QuestionPostReplyService {

    void addQuestionPostReply(QuestionPostReply questionPostReply);
}

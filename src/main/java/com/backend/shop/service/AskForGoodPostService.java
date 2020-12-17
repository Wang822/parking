package com.backend.shop.service;

import com.backend.shop.pojo.AskForGoodPost;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface AskForGoodPostService {

    List<AskForGoodPost> findAll();

    void addAskForGoodPost(AskForGoodPost askForGoodPost);
}

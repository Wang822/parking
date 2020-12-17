package com.backend.shop.controller;

import com.backend.shop.common.GlobalResult;
import com.backend.shop.pojo.QuestionPost;
import com.backend.shop.service.QuestionPostService;
import com.backend.shop.util.TokenUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qpost")
public class QuestionPostController {

    @Autowired
    private QuestionPostService questionPostService;

    @GetMapping("/findAll")
    public GlobalResult findAll(){
        List<QuestionPost> questionPosts = questionPostService.findAll();
        return new GlobalResult(200,"success",questionPosts);

    }

    @PostMapping("/add")
    public GlobalResult addQuestionPost(@RequestHeader(value = "Authorization") String token,
                                              @RequestParam String q_content,
                                              @RequestParam String q_title,
                                              @RequestParam String time
    ) throws ParseException {
        int userId = TokenUtil.getUserId(token);
        QuestionPost questionPost = new QuestionPost();
        questionPost.setUserId(userId);
        questionPost.setQContent(q_content);
        questionPost.setQTitle(q_title);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse(time);
        questionPost.setTime(date);
        // System.out.println("time");
        questionPostService.addQuestionPost(questionPost);
        return new GlobalResult(200, "successful operation");
    }

}

package com.backend.shop.controller;

import com.backend.shop.pojo.QuestionPost;
import com.backend.shop.service.QuestionPostService;
import com.backend.shop.util.TokenUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qpost")
public class QuestionPostController {

    @Autowired
    private QuestionPostService questionPostService;

    @GetMapping("/findAll")
    public ResponseEntity<List<QuestionPost>> findAll(){
        List<QuestionPost> questionPosts = questionPostService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(questionPosts);

    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestionPost(@RequestHeader(value = "Authorization") String token,
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
        return ResponseEntity.status(HttpStatus.OK).body("successful operation");
    }

    @DeleteMapping("/remove/{qPostId}")
    public ResponseEntity<String> deleteQuestionPost(@RequestHeader(value = "Authorization") String token,
                                                       @PathVariable int qPostId) {
        int userId = TokenUtil.getUserId(token);
        questionPostService.deleteQuestionPost(qPostId);
        return ResponseEntity.status(HttpStatus.OK).body("successful operation");
    }

}

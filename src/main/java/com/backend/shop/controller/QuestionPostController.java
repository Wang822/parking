package com.backend.shop.controller;

import com.backend.shop.pojo.QuestionPost;
import com.backend.shop.service.QuestionPostService;
import com.backend.shop.util.TokenUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "return all QuestionPosts")
    public ResponseEntity<List<QuestionPost>> findAll(){
        List<QuestionPost> questionPosts = questionPostService.findAll();
        for (QuestionPost questionPost : questionPosts) {
            questionPost.setNick_name(questionPostService.selectNickname(questionPost.getQPostId()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(questionPosts);

    }

    @GetMapping("/findAllById")
    @ApiOperation(value = "return all QuestionPosts")
    public ResponseEntity<List<QuestionPost>> findAllById(@RequestHeader(value = "Authorization") String token){
        int userId = TokenUtil.getUserId(token);
        List<QuestionPost> questionPosts = questionPostService.findAllById(userId);
        for (QuestionPost questionPost : questionPosts) {
            questionPost.setNick_name(questionPostService.selectNickname(questionPost.getQPostId()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(questionPosts);

    }

    @PostMapping("/add")
    @ApiOperation(value = "add a new QuestionPost")
    public ResponseEntity<String> addQuestionPost(@RequestHeader(value = "Authorization") String token,
                                                  @ApiParam("QuestionPost's content")@RequestParam String q_content,
                                                  @ApiParam("QuestionPost's title")@RequestParam String q_title,
                                                  @ApiParam("time, yyyy-MM-dd hh:mm:ss")@RequestParam String time
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
    @ApiOperation(value = "delete a QuestionPost")
    public ResponseEntity<String> deleteQuestionPost(@RequestHeader(value = "Authorization") String token,
                                                     @ApiParam("QuestionPost's id")int qPostId) {
        int userId = TokenUtil.getUserId(token);

        questionPostService.deleteQuestionPost(qPostId);
        questionPostService.deleteReplies(qPostId);
        return ResponseEntity.status(HttpStatus.OK).body("successful operation");
    }

    @ApiOperation(value = "get the number of replies in post")
    @GetMapping("/count")
    public ResponseEntity<Integer> getCount(@RequestHeader(value = "Authorization") String token,
                                            @ApiParam("AskForGoodPost's id")int qPostId) {
        int userId = TokenUtil.getUserId(token);
        int count = questionPostService.selectRCount(qPostId);
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }

}

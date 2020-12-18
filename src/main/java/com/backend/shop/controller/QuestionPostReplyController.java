package com.backend.shop.controller;

import com.backend.shop.pojo.QuestionPostReply;
import com.backend.shop.service.QuestionPostReplyService;
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
@RequestMapping("/qpostreply")
public class QuestionPostReplyController {
    @Autowired
    private QuestionPostReplyService questionPostReplyService;

    @PostMapping("/add")
    public ResponseEntity<String> addQuestionPostReply(@RequestHeader(value = "Authorization") String token,
                                              @RequestParam int q_post_id,
                                              @RequestParam String content,
                                              @RequestParam String time
    ) throws ParseException {
        int userId = TokenUtil.getUserId(token);
        QuestionPostReply questionPostReply = new QuestionPostReply();
        questionPostReply.setUserId(userId);
        questionPostReply.setQPostId(q_post_id);
        questionPostReply.setContent(content);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse(time);
        questionPostReply.setTime(date);
        // System.out.println("time");
        questionPostReplyService.addQuestionPostReply(questionPostReply);
        return ResponseEntity.status(HttpStatus.OK).body("successful operation");
    }

    @GetMapping("/get")
    public ResponseEntity<List<QuestionPostReply>> getQuestionPostReplies(@RequestHeader(value = "Authorization") String token,
                                                                          int qPostId) {
        int userId = TokenUtil.getUserId(token);
        List<QuestionPostReply> questionPostReplies = questionPostReplyService.selectQuestionPostReplies(qPostId);
        for (QuestionPostReply questionPostReply : questionPostReplies) {
            questionPostReply.setNick_name(questionPostReplyService.selectNickname(questionPostReply.getQReplyId()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(questionPostReplies);
    }

    @DeleteMapping("/remove/{qPostId}")
    public ResponseEntity<String> deleteQuestionPostReplies(@RequestHeader(value = "Authorization") String token,
                                                       @PathVariable int qPostId) {
        int userId = TokenUtil.getUserId(token);
        questionPostReplyService.deleteQuestionPostReplies(qPostId);
        return ResponseEntity.status(HttpStatus.OK).body("successful operation");
    }

    @DeleteMapping("/removeone/{qReplyId}")
    public ResponseEntity<String> deleteOneQuestionPostReply(@RequestHeader(value = "Authorization") String token,
                                                            @PathVariable int qReplyId) {
        int userId = TokenUtil.getUserId(token);
        questionPostReplyService.deleteOneQuestionPostReply(qReplyId);
        return ResponseEntity.status(HttpStatus.OK).body("successful operation");
    }
}

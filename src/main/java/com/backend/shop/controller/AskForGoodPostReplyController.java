package com.backend.shop.controller;

import com.backend.shop.pojo.AskForGoodPostReply;
import com.backend.shop.service.AskForGoodPostReplyService;
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
@RequestMapping("/afgpostreply")
public class AskForGoodPostReplyController {
    @Autowired
    private AskForGoodPostReplyService askForGoodPostReplyService;

    @PostMapping("/add")
    @ApiOperation(value = "add a new AskForGoodPostReply")
    public ResponseEntity<String> addAskForGoodPostReply(@RequestHeader(value = "Authorization") String token,
                                                       @ApiParam("AskForGoodPost's id")@RequestParam int afg_post_id,
                                                       @ApiParam("AskForGoodPostReply's content")@RequestParam String content,
                                                       @ApiParam("time, yyyy-MM-dd hh:mm:ss")@RequestParam String time
    ) throws ParseException {
        int userId = TokenUtil.getUserId(token);
        AskForGoodPostReply askForGoodPostReply = new AskForGoodPostReply();
        askForGoodPostReply.setUserId(userId);
        askForGoodPostReply.setAfgPostId(afg_post_id);
        askForGoodPostReply.setContent(content);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse(time);
        askForGoodPostReply.setTime(date);
        // System.out.println("time");
        askForGoodPostReplyService.addAskForGoodPostReply(askForGoodPostReply);
        return ResponseEntity.status(HttpStatus.OK).body("successful operation");
    }

    @GetMapping("/get")
    @ApiOperation(value = "return all AskForGoodPostReplies")
    public ResponseEntity<List<AskForGoodPostReply>> getAskForGoodPostReplies(@RequestHeader(value = "Authorization") String token,
                                                                          @ApiParam("AskForGoodPost's id")int afgPostId) {
        int userId = TokenUtil.getUserId(token);
        List<AskForGoodPostReply> askForGoodPostReplies = askForGoodPostReplyService.selectAskForGoodPostReplies(afgPostId);
        for (AskForGoodPostReply askForGoodPostReply : askForGoodPostReplies) {
            askForGoodPostReply.setNick_name(askForGoodPostReplyService.selectNickname(askForGoodPostReply.getAfgReplyId()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(askForGoodPostReplies);
    }

    @DeleteMapping("/remove/{afgPostId}")
    @ApiOperation(value = "delete an AskForGoodPost's replies")
    public ResponseEntity<String> deleteAskForGoodPostReplies(@RequestHeader(value = "Authorization") String token,
                                                            @ApiParam("AskForGoodPost's id")@PathVariable int afgPostId) {
        int userId = TokenUtil.getUserId(token);
        askForGoodPostReplyService.deleteAskForGoodPostReplies(afgPostId);
        return ResponseEntity.status(HttpStatus.OK).body("successful operation");
    }

    @DeleteMapping("/removeone/{afgReplyId}")
    @ApiOperation(value = "delete an AskForGoodPostReply")
    public ResponseEntity<String> deleteOneAskForGoodPostReply(@RequestHeader(value = "Authorization") String token,
                                                             @ApiParam("AskForGoodPostReply's id")@PathVariable int afgReplyId) {
        int userId = TokenUtil.getUserId(token);
        askForGoodPostReplyService.deleteOneAskForGoodPostReply(afgReplyId);
        return ResponseEntity.status(HttpStatus.OK).body("successful operation");
    }
}

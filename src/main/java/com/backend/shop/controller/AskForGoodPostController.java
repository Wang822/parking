package com.backend.shop.controller;

import com.backend.shop.pojo.AskForGoodPost;
import com.backend.shop.service.AskForGoodPostService;
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
@RequestMapping("/afgpost")
public class AskForGoodPostController {

    @Autowired
    private AskForGoodPostService askForGoodPostService;


    @GetMapping("/findAll")
    @ApiOperation(value = "return all AskForGoodPosts",response = AskForGoodPost.class)
    public ResponseEntity<List<AskForGoodPost>> findAll(){
        List<AskForGoodPost> askForGoodPosts = askForGoodPostService.findAll();
        for (AskForGoodPost askForGoodPost : askForGoodPosts) {
            askForGoodPost.setNick_name(askForGoodPostService.selectNickname(askForGoodPost.getAfgPostId()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(askForGoodPosts);

    }

    @GetMapping("/findAllById")
    @ApiOperation(value = "return all AskForGoodPosts",response = AskForGoodPost.class)
    public ResponseEntity<List<AskForGoodPost>> findAllById(@RequestHeader(value = "Authorization") String token){
        int userId = TokenUtil.getUserId(token);
        List<AskForGoodPost> askForGoodPosts = askForGoodPostService.findAllById(userId);
        for (AskForGoodPost askForGoodPost : askForGoodPosts) {
            askForGoodPost.setNick_name(askForGoodPostService.selectNickname(askForGoodPost.getAfgPostId()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(askForGoodPosts);

    }

    @PostMapping("/add")
    @ApiOperation(value = "add a new AskForGoodPost")
    public ResponseEntity<String> addAskForGoodPost(@RequestHeader(value = "Authorization") String token,
                                                    @ApiParam("AskForGoodPost's introduction")@RequestParam String afg_intro,
                                                    @ApiParam("AskForGoodPost's title")@RequestParam String afg_title,
                                                    @ApiParam("AskForGoodPost's tag")@RequestParam int afg_tag,
                                                    @ApiParam("user's campus")@RequestParam int campus,
                                                    @ApiParam("goods price")@RequestParam double afg_price,
                                                    @ApiParam("goods condition")@RequestParam String afg_condition,
                                                    @ApiParam("time, yyyy-MM-dd hh:mm:ss")@RequestParam String time
                                 ) throws ParseException {
        int userId = TokenUtil.getUserId(token);
        AskForGoodPost askForGoodPost = new AskForGoodPost();
        askForGoodPost.setUserId(userId);
        askForGoodPost.setAfgIntro(afg_intro);
        askForGoodPost.setAfgTitle(afg_title);
        askForGoodPost.setAfgTag(afg_tag);
        askForGoodPost.setCampus(campus);
        askForGoodPost.setAfgPrice(afg_price);
        askForGoodPost.setAfgCondition(afg_condition);
        //System.out.println("save");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse(time);
        askForGoodPost.setTime(date);
       // System.out.println("time");
        askForGoodPostService.addAskForGoodPost(askForGoodPost);
        return ResponseEntity.status(HttpStatus.OK).body("successful operation");
    }

    @DeleteMapping("/remove/{afgPostId}")
    @ApiOperation(value = "delete an AskForGoodPost")
    public ResponseEntity<String> deleteAskForGoodPost(@RequestHeader(value = "Authorization") String token,
                                                       @ApiParam("AskForGoodPost's id")int afgPostId) {
        int userId = TokenUtil.getUserId(token);
        askForGoodPostService.deleteAskForGoodPost(afgPostId);
        askForGoodPostService.deleteReplies(afgPostId);
        return ResponseEntity.status(HttpStatus.OK).body("successful operation");
    }

    @ApiOperation(value = "get the number of replies in post")
    @GetMapping("/count")
    public ResponseEntity<Integer> getCount(@RequestHeader(value = "Authorization") String token,
                                                 @ApiParam("AskForGoodPost's id")int afgPostId) {
        int userId = TokenUtil.getUserId(token);
        int count = askForGoodPostService.selectRCount(afgPostId);
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }

}

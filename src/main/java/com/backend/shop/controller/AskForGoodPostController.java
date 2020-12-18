package com.backend.shop.controller;

import com.backend.shop.pojo.AskForGoodPost;
import com.backend.shop.service.AskForGoodPostService;
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
@RequestMapping("/afgpost")
public class AskForGoodPostController {

    @Autowired
    private AskForGoodPostService askForGoodPostService;

    @GetMapping("/findAll")
    public ResponseEntity<List<AskForGoodPost>> findAll(){
        List<AskForGoodPost> askForGoodPosts = askForGoodPostService.findAll();
        for (AskForGoodPost askForGoodPost : askForGoodPosts) {
            askForGoodPost.setNick_name(askForGoodPostService.selectNickname(askForGoodPost.getAfgPostId()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(askForGoodPosts);

    }

    @PostMapping("/add")
    public ResponseEntity<String> addAskForGoodPost(@RequestHeader(value = "Authorization") String token,
                                 @RequestParam String afg_intro,
                                 @RequestParam String afg_title,
                                 @RequestParam int afg_tag,
                                 @RequestParam int campus,
                                 @RequestParam double afg_price,
                                 @RequestParam String afg_condition,
                                 @RequestParam String time
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
    public ResponseEntity<String> deleteAskForGoodPost(@RequestHeader(value = "Authorization") String token,
                                              @PathVariable int afgPostId) {
        int userId = TokenUtil.getUserId(token);
        askForGoodPostService.deleteAskForGoodPost(afgPostId);
        return ResponseEntity.status(HttpStatus.OK).body("successful operation");
    }

}

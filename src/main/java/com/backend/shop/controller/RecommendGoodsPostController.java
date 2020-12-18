package com.backend.shop.controller;


import com.backend.shop.pojo.RecommendGoodsPost;
import com.backend.shop.service.RecommendGoodsPostService;
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
@RequestMapping("/rgpost")
public class RecommendGoodsPostController {

    @Autowired
    private RecommendGoodsPostService recommendGoodsPostService;

    @GetMapping("/findAll")
    public ResponseEntity<List<RecommendGoodsPost>> findAll(){
        List<RecommendGoodsPost> recommendGoodsPosts = recommendGoodsPostService.findAll();
        for (RecommendGoodsPost recommendGoodsPost : recommendGoodsPosts) {
            recommendGoodsPost.setNick_name(recommendGoodsPostService.selectNickname(recommendGoodsPost.getRgPostId()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(recommendGoodsPosts);

    }

    @PostMapping("/add")
    public ResponseEntity<String> addRecommendGoodsPost(@RequestHeader(value = "Authorization") String token,
                                 @RequestParam String rg_intro,
                                 @RequestParam String rg_title,
                                 @RequestParam int rg_tag,
                                 @RequestParam String time
    ) throws ParseException {
        int userId = TokenUtil.getUserId(token);
        RecommendGoodsPost recommendGoodsPost = new RecommendGoodsPost();
        recommendGoodsPost.setUserId(userId);
        recommendGoodsPost.setRgIntro(rg_intro);
        recommendGoodsPost.setRgTag(rg_tag);
        recommendGoodsPost.setRgTitle(rg_title);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse(time);
        recommendGoodsPost.setTime(date);
        // System.out.println("time");
        recommendGoodsPostService.addRecommendGoodsPost(recommendGoodsPost);
        return ResponseEntity.status(HttpStatus.OK).body("successful operation");
    }

    @DeleteMapping("/remove/{rgPostId}")
    public ResponseEntity<String> deleteRecommendGoodsPost(@RequestHeader(value = "Authorization") String token,
                                                       @PathVariable int rgPostId) {
        int userId = TokenUtil.getUserId(token);
        recommendGoodsPostService.deleteRecommendGoodsPost(rgPostId);
        return ResponseEntity.status(HttpStatus.OK).body("successful operation");
    }

}

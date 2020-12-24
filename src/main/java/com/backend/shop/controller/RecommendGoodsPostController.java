package com.backend.shop.controller;


import com.backend.shop.pojo.AskForGoodPost;
import com.backend.shop.pojo.RecommendGoodsPost;
import com.backend.shop.service.RecommendGoodsPostService;
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
@RequestMapping("/rgpost")
public class RecommendGoodsPostController {

    @Autowired
    private RecommendGoodsPostService recommendGoodsPostService;

    @GetMapping("/findAll")
    @ApiOperation(value = "return all RecommendGoodsPosts",response = RecommendGoodsPost.class)
    public ResponseEntity<List<RecommendGoodsPost>> findAll(){
        List<RecommendGoodsPost> recommendGoodsPosts = recommendGoodsPostService.findAll();
        for (RecommendGoodsPost recommendGoodsPost : recommendGoodsPosts) {
            recommendGoodsPost.setNick_name(recommendGoodsPostService.selectNickname(recommendGoodsPost.getRgPostId()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(recommendGoodsPosts);

    }

    @PostMapping("/add")
    @ApiOperation(value = "add a new RecommendGoodsPost")
    public ResponseEntity<String> addRecommendGoodsPost(@RequestHeader(value = "Authorization") String token,
                                                        @ApiParam("RecommendGoodsPost's introduction")@RequestParam String rg_intro,
                                                        @ApiParam("RecommendGoodsPost's title")@RequestParam String rg_title,
                                                        @ApiParam("RecommendGoodsPost's tag")@RequestParam int rg_tag,
                                                        @ApiParam("time, yyyy-MM-dd hh:mm:ss")@RequestParam String time,
                                                                    @ApiParam("avatar")@RequestParam String avatar
    ) throws ParseException {
        int userId = TokenUtil.getUserId(token);
        RecommendGoodsPost recommendGoodsPost = new RecommendGoodsPost();
        recommendGoodsPost.setUserId(userId);
        recommendGoodsPost.setRgIntro(rg_intro);
        recommendGoodsPost.setRgTag(rg_tag);
        recommendGoodsPost.setRgTitle(rg_title);
        recommendGoodsPost.setAvatar(avatar);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse(time);
        recommendGoodsPost.setTime(date);
        // System.out.println("time");
        recommendGoodsPostService.addRecommendGoodsPost(recommendGoodsPost);
        return ResponseEntity.status(HttpStatus.OK).body("successful operation");
    }

    @DeleteMapping("/remove/{rgPostId}")
    @ApiOperation(value = "delete an RecommendGoodsPost")
    public ResponseEntity<String> deleteRecommendGoodsPost(@RequestHeader(value = "Authorization") String token,
                                                           @ApiParam("RecommendGoodsPost's id")@PathVariable int rgPostId) {
        int userId = TokenUtil.getUserId(token);
        if(recommendGoodsPostService.findPostUserId(rgPostId)==userId) {
            recommendGoodsPostService.deleteRecommendGoodsPost(rgPostId);
            return ResponseEntity.status(HttpStatus.OK).body("successful operation");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body("delete denied");
        }
    }

}

package com.backend.shop.controller;


import com.backend.shop.common.GlobalResult;
import com.backend.shop.pojo.RecommendGoodsPost;
import com.backend.shop.service.RecommendGoodsPostService;
import com.backend.shop.util.TokenUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rgpost")
public class RecommendGoodsPostController {

    @Autowired
    private RecommendGoodsPostService recommendGoodsPostService;

    @GetMapping("/findAll")
    public GlobalResult findAll(){
        List<RecommendGoodsPost> recommendGoodsPosts = recommendGoodsPostService.findAll();
        return new GlobalResult(200,"success",recommendGoodsPosts);

    }

    @PostMapping("/add")
    public GlobalResult addRecommendGoodsPost(@RequestHeader(value = "Authorization") String token,
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
        return new GlobalResult(200, "successful operation");
    }

}

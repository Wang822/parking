package com.backend.shop.controller;

import com.backend.shop.common.GlobalResult;
import com.backend.shop.pojo.Favorite;
import com.backend.shop.serviece.FavoriteService;
import com.backend.shop.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/add")
    public GlobalResult addFavor(@RequestHeader(value = "Authorization") String token,
                                 @RequestParam int good_id) {
        int userId = TokenUtil.getUserId(token);
        Favorite favorite = new Favorite();
        favorite.setGoodId(good_id);
        favorite.setUserId(userId);
        int exist = favoriteService.selectFavor(favorite);
        if (exist != 0) {
            return new GlobalResult(400, "Already added");
        }
        favoriteService.addFavor(favorite);
        return new GlobalResult(200, "successful operation");
    }
}

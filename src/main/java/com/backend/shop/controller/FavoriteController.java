package com.backend.shop.controller;

import com.backend.shop.common.GlobalResult;
import com.backend.shop.pojo.Favorite;
import com.backend.shop.pojo.Good;
import com.backend.shop.service.FavoriteService;
import com.backend.shop.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

    @GetMapping("/get")
    public GlobalResult getContacts(@RequestHeader(value = "Authorization") String token) {
        int userId = TokenUtil.getUserId(token);
        ArrayList<Good> goods = favoriteService.selectGoods(userId);
        return new GlobalResult(200, "successful operation", goods);
    }

    @DeleteMapping("/remove/{goodId}")
    public GlobalResult deleteFavor(@RequestHeader(value = "Authorization") String token,
                                    @PathVariable int goodId) {
        int userId = TokenUtil.getUserId(token);
        favoriteService.deleteFavorite(userId, goodId);
        return new GlobalResult(200, "successful operation");
    }

    @GetMapping("/count")
    public GlobalResult getCount(@RequestHeader(value = "Authorization") String token) {
        int userId = TokenUtil.getUserId(token);
        int count = favoriteService.selectCount(userId);
        return new GlobalResult(200, "successful operation", count);
    }
}

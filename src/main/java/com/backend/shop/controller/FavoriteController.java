package com.backend.shop.controller;

import com.backend.shop.common.GlobalResult;
import com.backend.shop.pojo.Favorite;
import com.backend.shop.pojo.Good;
import com.backend.shop.service.FavoriteService;
import com.backend.shop.util.TokenUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @ApiOperation(value = "add a good into favorite: if already exist, return code 208, else return 200")
    @PostMapping("/add")
    public ResponseEntity<String> addFavor(@RequestHeader(value = "Authorization") String token,
                                           @ApiParam(value = "good's id", example = "1") @RequestParam int good_id) {
        int userId = TokenUtil.getUserId(token);
        Favorite favorite = new Favorite();
        favorite.setGoodId(good_id);
        favorite.setUserId(userId);
        int exist = favoriteService.selectFavor(favorite);
        if (exist != 0) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("already exist");
        }
        favoriteService.addFavor(favorite);
        return ResponseEntity.status(HttpStatus.OK).body("successful operation");
    }

    @ApiOperation(value = "get all favorite goods of the user")
    @GetMapping("/get")
    public ResponseEntity<ArrayList<Good>> getFavorites(@RequestHeader(value = "Authorization") String token) {
        int userId = TokenUtil.getUserId(token);
        ArrayList<Good> goods = favoriteService.selectGoods(userId);
        return ResponseEntity.status(HttpStatus.OK).body(goods);
    }

    @ApiOperation(value = "get all favorite goods of the user by id")
    @GetMapping("/getById")
    public ResponseEntity<ArrayList<Good>> getFavoritesById(
            @ApiParam(value = "user id", example = "11") @RequestParam int userId) {
        ArrayList<Good> goods = favoriteService.selectGoods(userId);
        return ResponseEntity.status(HttpStatus.OK).body(goods);
    }

    @ApiOperation(value = "remove a good")
    @PostMapping("/remove")
    public ResponseEntity<String> deleteFavor(@RequestHeader(value = "Authorization") String token,
                                              @ApiParam(value = "good's id", example = "2") @RequestParam int goodId) {
        int userId = TokenUtil.getUserId(token);
        favoriteService.deleteFavorite(userId, goodId);
        return ResponseEntity.status(HttpStatus.OK).body("successful operation");
    }

    @ApiOperation(value = "get the number of goods in user's favorite")
    @GetMapping("/count")
    public ResponseEntity<Integer> getCount(@RequestHeader(value = "Authorization") String token) {
        int userId = TokenUtil.getUserId(token);
        int count = favoriteService.selectCount(userId);
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }
}

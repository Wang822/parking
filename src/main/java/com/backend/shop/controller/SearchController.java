package com.backend.shop.controller;

import com.backend.shop.common.GlobalResult;
import com.backend.shop.pojo.Good;
import com.backend.shop.service.SearchService;
import com.backend.shop.util.TokenUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Api
@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/getRecommend")
    public GlobalResult getContacts(@RequestHeader(value = "Authorization") String token) {
        int userId = TokenUtil.getUserId(token);
        ArrayList<Good> goods = searchService.getRecommend(userId);
        return new GlobalResult(200, "successful operation", goods);
    }

    @GetMapping("")
    public GlobalResult getByName(@RequestHeader(value = "Authorization") String token,
                                  @RequestParam(value = "name") String name,
                                  @RequestParam(value = "tag", required = false, defaultValue = "-1") int tag,
                                  @RequestParam(value = "highPrice", required = false, defaultValue = "-1") double highPrice,
                                  @RequestParam(value = "lowPrice", required = false, defaultValue = "-1") double lowPrice) {
        if (highPrice == -1) {
            highPrice = 10000000;
        }
        if (lowPrice == -1) {
            lowPrice = 0;
        }
        ArrayList<Good> goods;
        if (tag == -1) {
            goods = searchService.search(name, lowPrice, highPrice);
        } else {
            goods = searchService.searchWithTag(name, tag, lowPrice, highPrice);
        }
        return new GlobalResult(200, "successful operation", goods);
    }
}

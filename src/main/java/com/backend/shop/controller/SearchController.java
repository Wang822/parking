package com.backend.shop.controller;

import com.backend.shop.common.GlobalResult;
import com.backend.shop.pojo.Good;
import com.backend.shop.service.SearchService;
import com.backend.shop.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Api
@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @ApiOperation(value = "get all goods (without param)")
    @GetMapping("/getRecommend")
    public ResponseEntity<ArrayList<Good>> getRecommend() {
        ArrayList<Good> goods = searchService.getRecommend();
        return ResponseEntity.status(HttpStatus.OK).body(goods);
    }

    @ApiOperation(value = "get recommend goods by tag")
    @GetMapping("/byTag")
    public ResponseEntity<ArrayList<Good>> getRecommendByTag(@ApiParam(example = "2") @RequestParam(value = "tag") int tag) {
        ArrayList<Good> goods = searchService.getRecommendByTag(tag);
        return ResponseEntity.status(HttpStatus.OK).body(goods);
    }

    @ApiOperation(value = "get recommend goods by campus")
    @GetMapping("/byCampus")
    public ResponseEntity<ArrayList<Good>> getRecommendByCampus(@ApiParam(example = "2") @RequestParam(value = "campus") int campus) {
        ArrayList<Good> goods = searchService.getRecommendByCampus(campus);
        return ResponseEntity.status(HttpStatus.OK).body(goods);
    }

    @ApiOperation(value = "search for goods")
    @GetMapping("")
    public ResponseEntity<ArrayList<Good>> search(@RequestHeader(value = "Authorization") String token,
                                                  @ApiParam(example = "软件工程") @RequestParam(value = "name") String name,
                                                  @ApiParam(example = "1") @RequestParam(value = "tag", required = false, defaultValue = "-1") int tag,
                                                  @ApiParam(example = "30") @RequestParam(value = "highPrice", required = false, defaultValue = "-1") double highPrice,
                                                  @ApiParam(example = "5") @RequestParam(value = "lowPrice", required = false, defaultValue = "-1") double lowPrice) {
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
        return ResponseEntity.status(HttpStatus.OK).body(goods);
    }
}

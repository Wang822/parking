package com.backend.shop.controller;

import com.backend.shop.common.GlobalResult;
import com.backend.shop.pojo.Good;
import com.backend.shop.pojo.User;
import com.backend.shop.serviece.GoodService;
import com.backend.shop.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/Good")
public class GoodController {
    @Autowired
    private GoodService goodService;

    @DeleteMapping("/remove/{goodId}")
    public GlobalResult deleteGood(@PathVariable int goodId)
    {
        goodService.deleteGood(goodId);
        return new GlobalResult(200,"successful operation");
    }

    @PutMapping("/revise")
    public GlobalResult reviseGood(@RequestBody Good good)
    {
        goodService.reviseGood(good);
        return new GlobalResult(200,"successful operation");
    }

    @PostMapping("/add")
    public GlobalResult addGood(@RequestBody Good good)
    {
        goodService.addGood(good);
        return new GlobalResult(200,"successful operation");
    }

    @GetMapping("/getItem/{goodId}")
    public GlobalResult getGood(@PathVariable int goodId)
    {
        goodService.getGood(goodId);
        return new GlobalResult(200,"successful operation");
    }
}

package com.backend.shop.controller;

import com.backend.shop.common.GlobalResult;
import com.backend.shop.pojo.Good;
import com.backend.shop.pojo.User;
import com.backend.shop.service.GoodService;
import com.backend.shop.util.TokenUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/Good")
public class GoodController {
    @Autowired
    private GoodService goodService;


    public Map<String, Object> getBackValue(boolean flag, Object message) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (flag==false) {
            map.put("result_code", 0);
        } else {
            map.put("result_code", 1);
        }
        map.put("result_body", message);
        return map;
    }

    @ApiOperation(value="remove a good")
    @DeleteMapping("/remove/{goodId}")
    public ResponseEntity<String> deleteGood(@PathVariable int goodId)
    {
//        boolean flag=true;
//        Map<String, Object> resultMap = new HashMap<>();
        if(goodService.existGood(goodId)==0)
        {
//            flag=false;
//            resultMap=getBackValue(flag,"Good with this goodId does not exist.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Good with this goodId does not exist.");
        }
        goodService.deleteGood(goodId);
//        resultMap=getBackValue(flag,"Successful operation.");
        return ResponseEntity.status(HttpStatus.OK).body("Successful operation.");
    }

    @PutMapping("/revise")
    public ResponseEntity<String> reviseGood(@RequestHeader(value="Authorization") String token,
                                   @RequestParam int good_id,
                                   @RequestParam String name,
                                   @RequestParam int status,
                                   @RequestParam String price,
                                   @RequestParam String description,
                                   @RequestParam int campus,
                                   @RequestParam int old,
                                   @RequestParam String pic_addr,
                                   @RequestParam String deposit,
                                   @RequestParam int tag,
                                   @RequestParam Date publish_date)
    {
//        boolean flag=true;
//        Map<String, Object> resultMap = new HashMap<>();
        int sellerId= TokenUtil.getUserId(token);
        if(goodService.existGood(good_id)==0)
        {
//            flag=false;
//            resultMap=getBackValue(flag,"Good with this goodId does not exist.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Good with this goodId does not exist.");
        }
        Good good=new Good();
        good.setGood_id(good_id);
        good.setName(name);
        good.setStatus(status);
        good.setPrice(price);
        good.setDescription(description);
        good.setCampus(campus);
        good.setOld(old);
        good.setSeller_id(sellerId);
        good.setPic_addr(pic_addr);
        good.setDeposit(deposit);
        good.setTag(tag);
        good.setPublish_date(publish_date);
        goodService.reviseGood(good);
//        resultMap=getBackValue(flag,"Successful operation.");
        return ResponseEntity.status(HttpStatus.OK).body("Successful operation.");
    }

    @PostMapping("/add")
    public ResponseEntity<String> addGood(@RequestHeader(value="Authorization") String token,
                                          @RequestParam int good_id,
                                          @RequestParam String name,
                                          @RequestParam int status,
                                          @RequestParam String price,
                                          @RequestParam String description,
                                          @RequestParam int campus,
                                          @RequestParam int old,
                                          @RequestParam String pic_addr,
                                          @RequestParam String deposit,
                                          @RequestParam int tag,
                                          @RequestParam Date publish_date)
    {
//        boolean flag=true;
//        Map<String, Object> resultMap = new HashMap<>();
        int sellerId= TokenUtil.getUserId(token);
        if(goodService.existGood(good_id)!=0)
        {
//            flag=false;
//            resultMap=getBackValue(flag,"Good with this good_id already exists.");
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Good with this good_id already exists.");
        }
        Good good=new Good();
        good.setGood_id(good_id);
        good.setName(name);
        good.setStatus(status);
        good.setPrice(price);
        good.setDescription(description);
        good.setCampus(campus);
        good.setOld(old);
        good.setSeller_id(sellerId);
        good.setPic_addr(pic_addr);
        good.setDeposit(deposit);
        good.setTag(tag);
        good.setPublish_date(publish_date);
        goodService.addGood(good);
//        resultMap=getBackValue(flag,"Successful operation.");
        return ResponseEntity.status(HttpStatus.OK).body("Successful operation.");
    }

    @GetMapping("/getItem/{goodId}")
    public ResponseEntity<Good> getGood(@PathVariable int goodId)
    {
//        boolean flag=true;
//        Map<String, Object> resultMap = new HashMap<>();
//        if(goodService.existGood(goodId)==0)
//        {
//            flag=false;
//            resultMap=getBackValue(flag,"Good with this goodId does not exist.");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultMap);
//        }
        Good good=goodService.getGood(goodId);
        //resultMap=getBackValue(flag,good);
        return ResponseEntity.status(HttpStatus.OK).body(good);
    }

    @GetMapping("/getItemList/{currPage}/{pageSize}")
    public ResponseEntity<Page<Good>> getGoodByPage(@PathVariable int currPage,@PathVariable int pageSize)
    {
        Page<Good> page=new Page<>(currPage,pageSize);
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("result_code",1);
//        resultMap.put("result_body",goodService.getGoodByPage(page));
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
    @GetMapping("/getGoodOnSail")
    public ResponseEntity<Integer> getGoodOnSailCount(@RequestHeader(value = "Authorization") String token)
    {
        int userId= TokenUtil.getUserId(token);
        int count=goodService.getGoodOnSailCount(userId);
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }
}

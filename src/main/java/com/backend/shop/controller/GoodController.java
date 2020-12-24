package com.backend.shop.controller;

import com.backend.shop.pojo.Good;
import com.backend.shop.service.GoodService;
import com.backend.shop.util.TokenUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


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
    @PostMapping("/remove/{gid}")
    public ResponseEntity<String> deleteGood(@RequestParam int gid)
    {
//        boolean flag=true;
//        Map<String, Object> resultMap = new HashMap<>();
        if(goodService.existGood(gid)==0)
        {
//            flag=false;
//            resultMap=getBackValue(flag,"Good with this goodId does not exist.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Good with this goodId does not exist.");
        }
        goodService.deleteGood(gid);
//        resultMap=getBackValue(flag,"Successful operation.");
        return ResponseEntity.status(HttpStatus.OK).body("Successful operation.");
    }

    @PostMapping("/revise")
    public ResponseEntity<String> reviseGood(@RequestHeader(value="Authorization") String token,
                                   @RequestParam int gid,
                                   @RequestParam String name,
                                   @RequestParam double price,
                                   @RequestParam String description,
                                   @RequestParam int campus,
                                   @RequestParam int old,
                                   @RequestParam String pic,
                                   @RequestParam int tag)
    {
//        boolean flag=true;
//        Map<String, Object> resultMap = new HashMap<>();
        int sellerId= TokenUtil.getUserId(token);
        if(goodService.existGood(gid)==0)
        {
//            flag=false;
//            resultMap=getBackValue(flag,"Good with this goodId does not exist.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Good with this goodId does not exist.");
        }
        Good good=new Good();
        good.setGid(gid);
        good.setName(name);
        good.setPrice(price);
        good.setDescription(description);
        good.setCampus(campus);
        good.setOld(old);
        good.setSeller(sellerId);
        good.setPic(pic);
        good.setTag(tag);
        goodService.reviseGood(good);
//        resultMap=getBackValue(flag,"Successful operation.");
        return ResponseEntity.status(HttpStatus.OK).body("Successful operation.");
    }

    @PostMapping("/add")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功添加商品"),
            @ApiResponse(code = 401, message = "token verify fail")})
    public ResponseEntity<Good> addGood(@RequestHeader(value="Authorization") String token,
                                          @RequestParam String name,
                                          @RequestParam double price,
                                          @RequestParam String description,
                                          @RequestParam int campus,
                                          @RequestParam int old,
                                          @RequestParam String pic,
                                          @RequestParam int tag)
    {
//        boolean flag=true;
//        Map<String, Object> resultMap = new HashMap<>();
        int sellerId= TokenUtil.getUserId(token);
//        Date publish_date = new Date();
//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
//        dateFormat.format(publish_date);
        String val = "";
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        int goodId=-1;
        try {
            goodId = Integer.valueOf(val).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        //int goodId=goodService.getAllGoodOnSailCount()+1;
        //Date date = new Date();
        //Timestamp publishDate = new Timestamp(date.getTime()); //2013-01-14 22:45:36.484
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date publishDate = new Date();
        Good good=new Good();
        good.setGid(goodId);
        good.setName(name);
        good.setPrice(price);
        good.setDescription(description);
        good.setCampus(campus);
        good.setOld(old);
        good.setSeller(sellerId);
        good.setPic(pic);
        good.setTag(tag);
        good.setPublish(publishDate);
        //good.toString();
        goodService.addGood(good);
        //System.out.println(good);
//        resultMap=getBackValue(flag,"Successful operation.");
        return ResponseEntity.status(HttpStatus.OK).body(good);
    }

    @GetMapping("/getItem/{gid}")
    public ResponseEntity<Good> getGood(@RequestParam int gid)
    {
//        boolean flag=true;
//        Map<String, Object> resultMap = new HashMap<>();
//        if(goodService.existGood(goodId)==0)
//        {
//            flag=false;
//            resultMap=getBackValue(flag,"Good with this goodId does not exist.");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultMap);
//        }
        Good good=goodService.getGood(gid);
        //resultMap=getBackValue(flag,good);
        return ResponseEntity.status(HttpStatus.OK).body(good);
    }

    @GetMapping("/getItemList/{currpage}/{pagesize}")
    public ResponseEntity<HashMap<String,Object>> getGoodByPage(@RequestHeader(value="Authorization") String token,@RequestParam int currpage,@RequestParam int pagesize)
    {
        Good good=new Good();
        int userId= TokenUtil.getUserId(token);
        IPage<Good> ipage=goodService.getGoodByPage(good,currpage,pagesize,userId);
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("result_code",1);
//        resultMap.put("result_body",goodService.getGoodByPage(page));
        HashMap<String,Object> resultmap=new HashMap<String,Object>();
        List<Good> goodlist=ipage.getRecords();
        int count=goodService.getGoodOnSailCount(userId);
        resultmap.put("total",count);
        resultmap.put("list",goodlist);
        return ResponseEntity.status(HttpStatus.OK).body(resultmap);
    }
    @GetMapping("/getGoodOnSail")
    public ResponseEntity<Integer> getGoodOnSailCount(@RequestHeader(value = "Authorization") String token)
    {
        int userId= TokenUtil.getUserId(token);
        int count=goodService.getGoodOnSailCount(userId);
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }
}

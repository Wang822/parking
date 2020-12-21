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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


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
    public ResponseEntity<String> deleteGood(@PathVariable int gid)
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

    @PutMapping("/revise")
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
        good.setGood_id(gid);
        good.setName(name);
        good.setPrice(price);
        good.setDescription(description);
        good.setCampus(campus);
        good.setOld(old);
        good.setSeller_id(sellerId);
        good.setPic_addr(pic);
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
        Date date = new Date();
        int goodId=goodService.getAllGoodOnSailCount()+1;
        Timestamp publishDate = new Timestamp(date.getTime()); //2013-01-14 22:45:36.484
        Good good=new Good();
        good.setGood_id(goodId);
        good.setName(name);
        good.setPrice(price);
        good.setDescription(description);
        good.setCampus(campus);
        good.setOld(old);
        good.setSeller_id(sellerId);
        good.setPic_addr(pic);
        good.setTag(tag);
        good.setPublish_date(publishDate);
        good.toString();
        goodService.addGood(good);
        //System.out.println(good);
//        resultMap=getBackValue(flag,"Successful operation.");
        return ResponseEntity.status(HttpStatus.OK).body(good);
    }

    @GetMapping("/getItem/{goodId}")
    public ResponseEntity<Good> getGood(@PathVariable int gid)
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

    @GetMapping("/getItemList/{currPage}/{pageSize}")
    public ResponseEntity<List<Good>> getGoodByPage(@RequestHeader(value="Authorization") String token,@PathVariable int currpage,@PathVariable int pagesize)
    {
        Good good=new Good();
        int userId= TokenUtil.getUserId(token);
        IPage<Good> ipage=goodService.getGoodByPage(good,currpage,pagesize,userId);
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("result_code",1);
//        resultMap.put("result_body",goodService.getGoodByPage(page));
        return ResponseEntity.status(HttpStatus.OK).body(ipage.getRecords());
    }
    @GetMapping("/getGoodOnSail")
    public ResponseEntity<Integer> getGoodOnSailCount(@RequestHeader(value = "Authorization") String token)
    {
        int userId= TokenUtil.getUserId(token);
        int count=goodService.getGoodOnSailCount(userId);
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }
}

package com.backend.shop.service.Impl;

import com.backend.shop.mapper.GoodDao;
import com.backend.shop.pojo.Good;
import com.backend.shop.service.GoodService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodDao goodDao;

    @Override
    public void addGood(Good good) {
        goodDao.addGood(good);
    }

    @Override
    public Good getGood(int goodId) {
        return goodDao.getGood(goodId);
    }

//    @Override
//    public void deleteGood(int goodId) { goodDao.deleteGood(goodId); }

    public void deleteGood(int goodId, int reason) { goodDao.soldOut(goodId, reason); }

    @Override
    public void reviseGood(Good good) {
        goodDao.reviseGood(good);
    }

    @Override
    public int existGood(int goodId) {
        return goodDao.existGood(goodId);
    }

    @Override
    public IPage<Good> getGoodByPage(Good good,int currPage,int pageSize, int userId) throws RuntimeException{
        try{
            Page<Good> p = new Page<>(currPage,pageSize);
            p.setRecords(goodDao.getGoodByPage(p,good,userId));
            return p;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    //public Page<Good> getGoodByPage(Page<Good> page, @Param("Good") Good good) {
    //    return getBaseMapper().getGoodByPage(page,good);
    //}
    @Override
    public int getGoodOnSailCount(int userId){return goodDao.getGoodOnSailCount(userId);}

    @Override
    public int getAllGoodOnSailCount(){return goodDao.getAllGoodOnSailCount();}

    @Override
    public List<Good> getGoodByUserId(int userid){return goodDao.getGoodByUserId(userid);}
}
package com.backend.shop.service.Impl;

import com.backend.shop.mapper.GoodDao;
import com.backend.shop.pojo.Good;
import com.backend.shop.service.GoodService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class GoodServiceImpl extends ServiceImpl<GoodDao,Good> implements GoodService {

    @Autowired
    private GoodDao goodDao;

    @Override
    public void addGood(Good good) {
        goodDao.addGood(good);
    }

    @Override
    public Good getGood(int good_id) {
        return goodDao.getGood(good_id);
    }

    @Override
    public void deleteGood(int good_id) {
        goodDao.deleteGood(good_id);
    }

    @Override
    public void reviseGood(Good good) {
        goodDao.reviseGood(good);
    }

    @Override
    public int existGood(int good_id) {
        return goodDao.existGood(good_id);
    }

    @Override
    public Page<Good> getGoodByPage(Page<Good> page) {
        return getBaseMapper().getGoodByPage(page);
    }
    //public Page<Good> getGoodByPage(Page<Good> page, @Param("Good") Good good) {
    //    return getBaseMapper().getGoodByPage(page,good);
    //}
    @Override
    public int getGoodOnSailCount(int userId){return goodDao.getGoodOnSailCount(userId);}
}
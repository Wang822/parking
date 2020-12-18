package com.backend.shop.serviece.Impl;

import com.backend.shop.mapper.GoodDao;
import com.backend.shop.pojo.Good;
import com.backend.shop.serviece.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class GoodServiceImpl implements GoodService{
    @Autowired
    private GoodDao goodDao;

    @Override
    public void addGood(Good good){goodDao.addGood(good);}

    @Override
    public Good getGood(int good_id){return goodDao.getGood(good_id);}

    @Override
    public void deleteGood(int good_id){goodDao.deleteGood(good_id);}

    @Override
    public void reviseGood(Good good){goodDao.reviseGood(good);}






}

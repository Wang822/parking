package com.backend.shop.service;

import com.backend.shop.pojo.Good;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface GoodService {
    void addGood(Good good);

    Good getGood(int goodId);

    void deleteGood(int goodId);

    void reviseGood(Good good);

    int existGood(int goodId);

    int getGoodOnSailCount(int userId);
    //Page<Good> getGoodByPage(Page<Good> page, @Param("Good") Good good);

    IPage<Good> getGoodByPage(Good good,int currPage,int pageSize, int userId);
    int getAllGoodOnSailCount();

    List<Good> getGoodByUserId(int userid);
}

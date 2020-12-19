package com.backend.shop.service;

import com.backend.shop.pojo.Good;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface GoodService extends IService<Good> {
    void addGood(Good good);

    Good getGood(int good_id);

    void deleteGood(int good_id);

    void reviseGood(Good good);

    int existGood(int good_id);

    int getGoodOnSailCount(int userId);
    //Page<Good> getGoodByPage(Page<Good> page, @Param("Good") Good good);

    Page<Good> getGoodByPage(Page<Good> page);
}

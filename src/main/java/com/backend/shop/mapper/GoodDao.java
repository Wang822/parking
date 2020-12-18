package com.backend.shop.mapper;

import com.backend.shop.pojo.Good;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Mapper
@Component
public interface GoodDao extends BaseMapper<Good> {
    void addGood(Good good);

    Good getGood(@Param("good_id")int good_id);

    void deleteGood(@Param("good_id") int good_id);

    void reviseGood(Good good);

    int existGood(@Param("good_id")int good_id);

    int getGoodOnSailCount(int userId);

    //Page<Good> getGoodByPage(Page<Good> page,@Param("Good") Good good);
    Page<Good> getGoodByPage(Page<Good> page);

}

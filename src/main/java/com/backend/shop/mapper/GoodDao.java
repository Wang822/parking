package com.backend.shop.mapper;

import com.backend.shop.pojo.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface GoodDao {
    void addGood(Good good);

    Good getGood(@Param("good_id")int good_id);

    void deleteGood(@Param("good_id") int good_id);

    void reviseGood(Good good);

    //仅增删改查，分页与昨晚说的尚未完成

}

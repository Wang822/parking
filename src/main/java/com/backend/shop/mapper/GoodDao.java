package com.backend.shop.mapper;

import com.backend.shop.pojo.Good;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface GoodDao extends BaseMapper<Good> {
    void addGood(Good good);

    Good getGood(@Param("gid")int gid);

    void deleteGood(@Param("gid") int gid);

    void reviseGood(@Param("good") Good good);

    int existGood(@Param("gid")int gid);

    int getGoodOnSailCount(@Param("userid") int userid);

    //Page<Good> getGoodByPage(Page<Good> page,@Param("Good") Good good);
    List<Good> getGoodByPage(Page<Good> page, @Param("good") Good good,@Param("userid") int userid);

    int getAllGoodOnSailCount();

}

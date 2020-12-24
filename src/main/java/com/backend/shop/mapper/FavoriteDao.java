package com.backend.shop.mapper;

import com.backend.shop.pojo.Favorite;
import com.backend.shop.pojo.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface FavoriteDao {
    void add(Favorite favorite);

    int selectFavor(Favorite favorite);

    ArrayList<Good> selectGoods(int userId);

    void deleteFavorite(@Param("userId") int userId, @Param("goodId") int goodId);

    int selectCount(int userId);

    void deleteFavoritesByGoodId(int goodId);
}

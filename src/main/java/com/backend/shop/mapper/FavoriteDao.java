package com.backend.shop.mapper;

import com.backend.shop.pojo.Favorite;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FavoriteDao {
    void add(Favorite favorite);

    int selectFavor(Favorite favorite);
}

package com.backend.shop.mapper;

import com.backend.shop.pojo.Good;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.ArrayList;

@Mapper
public interface SearchDao {
    ArrayList<Good> search(@Param("name") String name,
                           @Param("lowPrice") double lowPrice,
                           @Param("highPrice") double highPrice);

    ArrayList<Good> searchWithTag(@Param("name") String name,
                                  @Param("tag") int tag,
                                  @Param("lowPrice") double lowPrice,
                                  @Param("highPrice") double highPrice);

    ArrayList<Good> getRecommend();

    ArrayList<Good> getRecommendByTag(int tag);

    ArrayList<Good> getRecommendByCampus(int campus);
}

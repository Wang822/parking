package com.backend.shop.service;

import com.backend.shop.pojo.Good;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface SearchService {
    ArrayList<Good> search(String name, double lowPrice, double highPrice);

    ArrayList<Good> searchWithTag(String name, int tag, double lowPrice, double highPrice);

    ArrayList<Good> getRecommend(int userId);

}

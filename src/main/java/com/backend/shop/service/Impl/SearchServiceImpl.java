package com.backend.shop.service.Impl;

import com.backend.shop.mapper.SearchDao;
import com.backend.shop.pojo.Good;
import com.backend.shop.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SearchDao searchDao;

    @Override
    public ArrayList<Good> search(String name, double lowPrice, double highPrice) {
        return searchDao.search(name, lowPrice, highPrice);
    }

    @Override
    public ArrayList<Good> searchWithTag(String name, int tag, double lowPrice, double highPrice) {
        return searchDao.searchWithTag(name, tag, lowPrice, highPrice);
    }

    @Override
    public ArrayList<Good> getRecommendByCampus(int campus) {
        return searchDao.getRecommendByCampus(campus);
    }

    @Override
    public ArrayList<Good> getRecommendByTag(int tag) {
        return searchDao.getRecommendByTag(tag);
    }

    @Override
    public ArrayList<Good> getRecommend() {
        return searchDao.getRecommend();
    }
}

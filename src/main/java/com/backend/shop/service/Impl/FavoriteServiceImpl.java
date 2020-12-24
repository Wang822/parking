package com.backend.shop.service.Impl;

import com.backend.shop.mapper.FavoriteDao;
import com.backend.shop.pojo.Favorite;
import com.backend.shop.pojo.Good;
import com.backend.shop.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteDao favoriteDao;

    @Override
    public void addFavor(Favorite favorite) {
        favoriteDao.add(favorite);
    }

    @Override
    public int selectFavor(Favorite favorite) {
        return favoriteDao.selectFavor(favorite);
    }

    @Override
    public ArrayList<Good> selectGoods(int userId) {
        return favoriteDao.selectGoods(userId);
    }

    @Override
    public void deleteFavorite(int userId, int goodId) {
        favoriteDao.deleteFavorite(userId, goodId);
    }

    @Override
    public int selectCount(int userId) {
        return favoriteDao.selectCount(userId);
    }

    @Override
    public void deleteFavoritesByGoodId(int goodId) {
        favoriteDao.deleteFavoritesByGoodId(goodId);
    }
}

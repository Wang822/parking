package com.backend.shop.serviece.Impl;

import com.backend.shop.mapper.FavoriteDao;
import com.backend.shop.pojo.Favorite;
import com.backend.shop.serviece.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}

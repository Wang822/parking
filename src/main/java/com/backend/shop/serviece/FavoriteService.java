package com.backend.shop.serviece;

import com.backend.shop.pojo.Favorite;
import com.backend.shop.pojo.Good;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface FavoriteService {
    void addFavor(Favorite favorite);

    int selectFavor(Favorite favorite);

    ArrayList<Good> selectGoods(int userId);

    void deleteFavorite(int userId, int goodId);
}

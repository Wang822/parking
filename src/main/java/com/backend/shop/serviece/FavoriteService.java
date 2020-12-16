package com.backend.shop.serviece;

import com.backend.shop.pojo.Favorite;
import org.springframework.stereotype.Service;

@Service
public interface FavoriteService {
    void addFavor(Favorite favorite);

    int selectFavor(Favorite favorite);
}

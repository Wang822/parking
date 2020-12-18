package com.backend.shop.serviece;

import com.backend.shop.pojo.Good;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public interface GoodService {
    void addGood(Good good);

    Good getGood(int good_id);

    void deleteGood(int good_id);

    void reviseGood(Good good);
}

package com.backend.shop.mapper;

import com.backend.shop.pojo.Good;

import java.util.ArrayList;

public interface SearchDao {
    ArrayList<Good> search(String name);
}

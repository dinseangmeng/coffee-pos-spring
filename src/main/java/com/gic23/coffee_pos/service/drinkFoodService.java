package com.gic23.coffee_pos.service;

import java.util.List;

import com.gic23.coffee_pos.entity.drink_food;

public interface drinkFoodService {
    List<drink_food> list();

    List<drink_food> findByCategoryId(String categoryId);

    List<drink_food> findBytypeId(Integer typeId);

    Long countByCategoryCode(String categoryId);

    Long countAll();

    Long countBytypeId(Integer typeId);

    drink_food findById(Integer id);
}

package com.gic23.coffee_pos.service;

import java.util.List;

import com.gic23.coffee_pos.entity.drink_food;

public interface drinkFoodService {
    List<drink_food> list();

    List<drink_food> findByCategoryId(String categoryId);

    Long countByCategoryCode(String categoryId);

    Long countAll();
}

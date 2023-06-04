package com.gic23.coffee_pos.service;

import java.util.List;

import com.gic23.coffee_pos.entity.drink_food_size;

public interface drinkFoodSizeService {
    List<drink_food_size> list();

    List<drink_food_size> getByProductId(Integer productId);
}

package com.gic23.coffee_pos.service;

import java.util.List;

import com.gic23.coffee_pos.entity.drink_food_topping;

public interface drinkFoodToppingService {
    List<drink_food_topping> list();

    List<drink_food_topping> getByProductId(Integer productId);

    drink_food_topping getById(Integer id);
}

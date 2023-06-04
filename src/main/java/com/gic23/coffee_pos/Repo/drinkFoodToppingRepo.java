package com.gic23.coffee_pos.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gic23.coffee_pos.entity.drink_food_topping;

public interface drinkFoodToppingRepo extends JpaRepository<drink_food_topping, Integer> {
    List<drink_food_topping> findByProductId(Integer productId);
}

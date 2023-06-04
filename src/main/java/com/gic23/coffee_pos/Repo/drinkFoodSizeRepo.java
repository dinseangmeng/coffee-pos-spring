package com.gic23.coffee_pos.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gic23.coffee_pos.entity.drink_food_size;

public interface drinkFoodSizeRepo extends JpaRepository<drink_food_size, Integer> {
    List<drink_food_size> findByProductId(Integer productId);
}

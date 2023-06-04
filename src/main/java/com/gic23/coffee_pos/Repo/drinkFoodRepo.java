package com.gic23.coffee_pos.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gic23.coffee_pos.entity.drink_food;

public interface drinkFoodRepo extends JpaRepository<drink_food, Integer> {
    List<drink_food> findByCategoryCode(String categoryId);

    Long countByCategoryCode(String categoryId);
}

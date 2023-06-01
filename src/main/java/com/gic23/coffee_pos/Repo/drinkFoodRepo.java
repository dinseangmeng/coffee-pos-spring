package com.gic23.coffee_pos.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gic23.coffee_pos.entity.drink_food;

public interface drinkFoodRepo extends JpaRepository<drink_food, Integer> {

}

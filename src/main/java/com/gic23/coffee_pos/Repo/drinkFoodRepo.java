package com.gic23.coffee_pos.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gic23.coffee_pos.entity.drink_food;

public interface drinkFoodRepo extends JpaRepository<drink_food, Integer> {
    List<drink_food> findByCategoryCode(String categoryId);

    Long countByCategoryCode(String categoryId);

    Long countBytypeId(Integer typeId);

    List<drink_food> findBytypeId(Integer typeId);

    @Query(value = "SELECT MAX(id) + 1 AS next_id FROM drink_food", nativeQuery = true)
    Long getNewId();

}

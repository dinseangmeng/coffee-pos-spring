package com.gic23.coffee_pos.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gic23.coffee_pos.entity.category;

public interface categoryRepo extends JpaRepository<category, Integer> {
    category findByCode(String code);

    List<category> findBytypeId(Integer typeId);

    @Query(value = "SELECT MAX(id) + 1 AS next_id FROM category", nativeQuery = true)
    Long getNewId();
}

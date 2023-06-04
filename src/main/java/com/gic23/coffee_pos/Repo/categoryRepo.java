package com.gic23.coffee_pos.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gic23.coffee_pos.entity.category;

public interface categoryRepo extends JpaRepository<category, Integer> {
    category findByCode(String code);
}

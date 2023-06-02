package com.gic23.coffee_pos.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gic23.coffee_pos.entity.tables;

public interface tableRepo extends JpaRepository<tables, Integer> {

}

package com.gic23.coffee_pos.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gic23.coffee_pos.entity.reciept;

public interface recieptRepo extends JpaRepository<reciept, Integer> {
    reciept findByorderId(Integer orderId);
}
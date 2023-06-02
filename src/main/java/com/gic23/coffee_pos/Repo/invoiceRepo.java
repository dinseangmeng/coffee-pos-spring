package com.gic23.coffee_pos.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gic23.coffee_pos.entity.invoice;

public interface invoiceRepo extends JpaRepository<invoice, Integer> {

}

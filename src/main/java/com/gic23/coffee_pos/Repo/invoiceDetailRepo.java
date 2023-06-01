package com.gic23.coffee_pos.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gic23.coffee_pos.entity.invoice_detail;

public interface invoiceDetailRepo extends JpaRepository<invoice_detail, Integer> {

}

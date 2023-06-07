package com.gic23.coffee_pos.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gic23.coffee_pos.entity.invoice_detail;

public interface invoiceDetailRepo extends JpaRepository<invoice_detail, Integer> {
    List<invoice_detail> findByinvoiceId(Integer invoiceId);

    List<invoice_detail> findByproductId(Integer productId);

    Long countByproductId(Integer productId);
}

package com.gic23.coffee_pos.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gic23.coffee_pos.entity.invoice;

@Repository
public interface invoiceRepo extends JpaRepository<invoice, Integer> {

    invoice findByInvoiceCode(String invoiceCode);

    Long countBycashierId(Integer cashierId);

}

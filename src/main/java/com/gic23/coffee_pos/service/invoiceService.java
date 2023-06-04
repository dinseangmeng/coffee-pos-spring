package com.gic23.coffee_pos.service;

import java.util.List;

import com.gic23.coffee_pos.entity.invoice;

public interface invoiceService {
    List<invoice> list();

    invoice getByInvoiceCode(String invoiceCode);
}

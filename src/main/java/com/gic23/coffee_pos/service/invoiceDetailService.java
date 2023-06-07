package com.gic23.coffee_pos.service;

import java.util.List;

import com.gic23.coffee_pos.entity.invoice_detail;

public interface invoiceDetailService {
    List<invoice_detail> list();

    invoice_detail getById(Integer id);

    invoice_detail save(invoice_detail invoice_detail);

    String deleteById(Integer id);

    Double getTotalPriceByInvoiceId(Integer InvoiceId);

    List<invoice_detail> listByproductId(Integer productId);

    Long countByproductId(Integer productId);
}

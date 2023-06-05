package com.gic23.coffee_pos.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gic23.coffee_pos.Repo.invoiceRepo;
import com.gic23.coffee_pos.entity.invoice;
import com.gic23.coffee_pos.service.invoiceService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class invoiceServiceImp implements invoiceService {
    @Autowired
    private invoiceRepo eRepository;

    @Override
    public List<invoice> list() {
        // TODO Auto-generated method stub
        return eRepository.findAll();
    }

    @Override
    public invoice getByInvoiceCode(String invoiceCode) {
        return eRepository.findByInvoiceCode(invoiceCode);
    }

    @Override
    public invoice Save(invoice invoice) {
        // TODO Auto-generated method stub
        return eRepository.save(invoice);
    }
}

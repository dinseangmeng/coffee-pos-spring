package com.gic23.coffee_pos.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gic23.coffee_pos.Repo.invoiceDetailRepo;
import com.gic23.coffee_pos.entity.invoice_detail;
import com.gic23.coffee_pos.service.invoiceDetailService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class invoiceDetailServiceImp implements invoiceDetailService {

    @Autowired
    private invoiceDetailRepo eRepository;

    @Override
    public List<invoice_detail> list() {
        return eRepository.findAll();
    }

    @Override
    public invoice_detail save(invoice_detail invoice_detail) {
        return eRepository.save(invoice_detail);
    }

    @Override
    public invoice_detail getById(Integer id) {
        return eRepository.findById(id).get();
    }

    @Override
    public String deleteById(Integer id) {
        eRepository.deleteById(id);
        return "Delete Successfull";
    }
}

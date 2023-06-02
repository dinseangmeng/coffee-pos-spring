package com.gic23.coffee_pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gic23.coffee_pos.entity.invoice_detail;
import com.gic23.coffee_pos.service.implement.invoiceDetailServiceImp;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("v1/invoiceDetail")
@Slf4j
public class invoiceDetailController {

    @Autowired
    private invoiceDetailServiceImp invoiceDetailService;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<invoice_detail>> TableList() {
        return ResponseEntity.ok().body(invoiceDetailService.list());
    }

}

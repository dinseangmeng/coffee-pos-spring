package com.gic23.coffee_pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gic23.coffee_pos.entity.invoice;
import com.gic23.coffee_pos.service.implement.invoiceServiceImp;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("v1/invoice")
@Slf4j
public class invoiceController {

    @Autowired
    private invoiceServiceImp invoiceService;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<invoice>> TableList() {
        return ResponseEntity.ok().body(invoiceService.list());
    }
}

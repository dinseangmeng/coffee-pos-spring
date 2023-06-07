package com.gic23.coffee_pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gic23.coffee_pos.entity.type;
import com.gic23.coffee_pos.service.implement.typeServiceImp;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("v1/type")
@Slf4j
public class typeController {
    @Autowired
    private typeServiceImp typeService;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<type>> list() {
        return ResponseEntity.ok().body(typeService.list());
    }

}

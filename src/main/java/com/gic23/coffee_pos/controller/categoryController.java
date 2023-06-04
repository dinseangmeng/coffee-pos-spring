package com.gic23.coffee_pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gic23.coffee_pos.entity.category;
import com.gic23.coffee_pos.dto.CategoryWithDrinkCount;
import com.gic23.coffee_pos.service.implement.categoryServiceImp;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("v1/category")
@Slf4j
public class categoryController {

    @Autowired
    private categoryServiceImp categoryService;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<CategoryWithDrinkCount>> list() {
        return ResponseEntity.ok().body(categoryService.list());
    }

}

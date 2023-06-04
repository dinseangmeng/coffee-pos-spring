package com.gic23.coffee_pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gic23.coffee_pos.entity.drink_food_size;
import com.gic23.coffee_pos.service.implement.drinkFoodSizeServiceImp;

import lombok.extern.slf4j.Slf4j;

// import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("v1/drinkFoodSize")
@Slf4j
public class drinkFoodSizeController {
    @Autowired
    private drinkFoodSizeServiceImp drinkFoodSizeService;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<drink_food_size>> list() {
        return ResponseEntity.ok().body(drinkFoodSizeService.list());
    }

    @GetMapping("/{productId}")
    @ResponseBody
    public ResponseEntity<List<drink_food_size>> getByProductId(@PathVariable Integer productId) {
        log.info("Test {}", productId);
        return ResponseEntity.ok().body(drinkFoodSizeService.getByProductId(productId));
    }

}

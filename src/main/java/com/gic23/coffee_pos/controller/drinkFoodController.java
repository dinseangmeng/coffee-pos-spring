package com.gic23.coffee_pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gic23.coffee_pos.entity.drink_food;
import com.gic23.coffee_pos.service.implement.drinkFoodServiceImp;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("v1/drinkFood")
@Slf4j
public class drinkFoodController {

    @Autowired
    private drinkFoodServiceImp drinkFoodService;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<drink_food>> list() {
        return ResponseEntity.ok().body(drinkFoodService.list());
    }

}

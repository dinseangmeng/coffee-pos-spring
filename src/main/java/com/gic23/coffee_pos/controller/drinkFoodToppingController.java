package com.gic23.coffee_pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gic23.coffee_pos.entity.drink_food_topping;
import com.gic23.coffee_pos.service.implement.drinkFoodToppingServiceImp;

@Controller
@RequestMapping("v1/drinkFoodTopping")
public class drinkFoodToppingController {
    @Autowired
    private drinkFoodToppingServiceImp drinkFoodToppingService;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<drink_food_topping>> TableList() {
        return ResponseEntity.ok().body(drinkFoodToppingService.list());
    }

    @GetMapping("/{productId}")
    @ResponseBody
    public ResponseEntity<List<drink_food_topping>> getByProductId(@PathVariable Integer productId) {

        return ResponseEntity.ok().body(drinkFoodToppingService.getByProductId(productId));
    }
}

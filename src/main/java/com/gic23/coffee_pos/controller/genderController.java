package com.gic23.coffee_pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gic23.coffee_pos.entity.gender;
import com.gic23.coffee_pos.service.implement.genderServiceImp;
import com.gic23.coffee_pos.util.ResponseUtil;

@RestController
@CrossOrigin
@RequestMapping("api/gender")
public class genderController {
    @Autowired
    genderServiceImp genderService;

    @GetMapping
    public ResponseUtil list() {
        return new ResponseUtil("Success", "Listing Gender", genderService.list());
    }

    @PostMapping
    public ResponseUtil save(@RequestBody gender gender) {
        return new ResponseUtil("Success", "Add new Gender", genderService.save(gender));
    }
}

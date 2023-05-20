package com.gic23.coffee_pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gic23.coffee_pos.entity.gender;
import com.gic23.coffee_pos.service.implement.genderServiceImp;

@RestController
@CrossOrigin
@RequestMapping("/gender")
public class genderController {
    @Autowired genderServiceImp genderService;

    @GetMapping
    public List<gender> list(){
        return genderService.list();
    }

    @PostMapping
    public gender save(@RequestBody gender gender){
        return genderService.save(gender);
    }
}

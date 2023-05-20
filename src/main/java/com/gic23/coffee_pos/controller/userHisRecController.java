package com.gic23.coffee_pos.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gic23.coffee_pos.entity.user_history_recode;
import com.gic23.coffee_pos.service.implement.userHisRecServiceImp;



@RestController
@CrossOrigin
@RequestMapping("/history")
public class userHisRecController {
   
    @Autowired
    private userHisRecServiceImp userHisRecService;

    @GetMapping
    public List<user_history_recode> list(){
        return userHisRecService.list();
    }
}

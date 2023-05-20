package com.gic23.coffee_pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gic23.coffee_pos.entity.role;
import com.gic23.coffee_pos.service.implement.roleServiceImp;

@RestController
@CrossOrigin
@RequestMapping("/role")
public class roleController {
   
    @Autowired roleServiceImp roleService;

    @GetMapping
    public List<role> list(){
        return roleService.list();
    }

    @PostMapping
    public role save(@RequestBody role role){
        return roleService.save(role);
    }

}

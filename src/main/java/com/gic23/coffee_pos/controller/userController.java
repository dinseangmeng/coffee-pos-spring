package com.gic23.coffee_pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gic23.coffee_pos.entity.user;
import com.gic23.coffee_pos.service.implement.userServiceImp;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class userController {
    
    @Autowired
    private userServiceImp userService;


    @GetMapping
    public List<user> list(){
        return userService.list();
        
    }

    @PostMapping("/register")
    public user register(@RequestBody user user){
        String rawPassword = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(rawPassword);
        user.setPassword(hashedPassword);
        return userService.register(user);
    }

    @GetMapping("/cashier")
    public List<user> listCasheir(){
        return userService.listCasheir();
    }
    
}

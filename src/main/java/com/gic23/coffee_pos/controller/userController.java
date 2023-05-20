package com.gic23.coffee_pos.controller;

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
import com.gic23.coffee_pos.util.ResponseUtil;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class userController {

    @Autowired
    private userServiceImp userService;

    @GetMapping
    public ResponseUtil list() {
        return new ResponseUtil("successfull", "list successfull", userService.list());

    }

    @PostMapping("/register")
    public ResponseUtil register(@RequestBody user user) {
        String rawPassword = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(rawPassword);
        user.setPassword(hashedPassword);
        return new ResponseUtil("Success", "Register succeefull", userService.register(user));
    }

    @GetMapping("/cashier")
    public ResponseUtil listCasheir() {
        return new ResponseUtil("Success", "List casheir", userService.listCasheir());
    }

}

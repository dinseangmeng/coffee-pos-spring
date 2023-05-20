package com.gic23.coffee_pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gic23.coffee_pos.entity.role;
import com.gic23.coffee_pos.service.implement.roleServiceImp;
import com.gic23.coffee_pos.util.ResponseUtil;

@RestController
@CrossOrigin
@RequestMapping("api/role")
public class roleController {

    @Autowired
    roleServiceImp roleService;

    @GetMapping
    public ResponseUtil list() {
        return new ResponseUtil("Success", "Listing role", roleService.list());
    }

    @PostMapping
    public ResponseUtil save(@RequestBody role role) {
        return new ResponseUtil("Success", "Add role", roleService.save(role));
    }

}

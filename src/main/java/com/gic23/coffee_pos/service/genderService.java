package com.gic23.coffee_pos.service;

import java.util.List;

import com.gic23.coffee_pos.entity.gender;

public interface genderService {
    
    List<gender> list();
    gender save(gender gender);
}

package com.gic23.coffee_pos.service;

import java.util.List;

import com.gic23.coffee_pos.entity.type;

public interface typeService {
    List<type> list();

    type findById(Integer id);
}

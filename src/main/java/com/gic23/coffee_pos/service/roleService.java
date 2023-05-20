package com.gic23.coffee_pos.service;

import java.util.List;

import com.gic23.coffee_pos.entity.role;

public interface roleService {
    List<role> list();
    role save(role role);
}

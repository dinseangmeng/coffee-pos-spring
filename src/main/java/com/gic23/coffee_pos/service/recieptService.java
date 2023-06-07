package com.gic23.coffee_pos.service;

import java.util.List;

import com.gic23.coffee_pos.entity.reciept;

public interface recieptService {
    List<reciept> list();

    reciept findById(Integer id);

    reciept findByorderId(Integer id);

    reciept save(reciept reciept);
}

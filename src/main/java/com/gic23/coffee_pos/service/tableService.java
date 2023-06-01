package com.gic23.coffee_pos.service;

import java.util.List;
import java.util.Optional;

import com.gic23.coffee_pos.entity.tables;

public interface tableService {
    List<tables> listTable();

    Optional<tables> getById(Integer id);
}

package com.gic23.coffee_pos.service;

import java.util.List;

import com.gic23.coffee_pos.entity.user;

public interface userService {

    List<user> list();

    user save(user user);

    user findById(Integer id);

    List<user> listCasheir();
}

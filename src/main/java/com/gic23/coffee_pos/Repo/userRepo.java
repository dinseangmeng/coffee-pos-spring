package com.gic23.coffee_pos.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gic23.coffee_pos.entity.user;

public interface userRepo extends JpaRepository<user, Integer> {

    List<user> findByroleid(Integer roleId);

}

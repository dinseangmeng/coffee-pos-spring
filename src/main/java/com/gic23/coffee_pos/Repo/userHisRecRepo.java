package com.gic23.coffee_pos.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gic23.coffee_pos.entity.user_history_recode;

public interface userHisRecRepo extends JpaRepository<user_history_recode, Integer> {
    List<user_history_recode> findByUseridAndStatusid(Integer userid, Integer statusid);
}

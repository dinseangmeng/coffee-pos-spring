package com.gic23.coffee_pos.service;

import java.util.List;

import com.gic23.coffee_pos.entity.user_history_recode;

public interface userHisRecService {
    List<user_history_recode> list();

    user_history_recode save(user_history_recode user_history_recode);

    user_history_recode findLastLoginOfUser(Integer userid);
}

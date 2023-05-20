package com.gic23.coffee_pos.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gic23.coffee_pos.Repo.userHisRecRepo;
import com.gic23.coffee_pos.entity.user_history_recode;
import com.gic23.coffee_pos.service.userHisRecService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class userHisRecServiceImp implements userHisRecService{
    @Autowired
    private userHisRecRepo eRepository;

    @Override
    public List<user_history_recode> list() {
        return eRepository.findAll();
    } 
}

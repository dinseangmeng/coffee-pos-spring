package com.gic23.coffee_pos.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gic23.coffee_pos.Repo.drinkFoodRepo;
import com.gic23.coffee_pos.entity.drink_food;
import com.gic23.coffee_pos.service.drinkFoodService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class drinkFoodServiceImp implements drinkFoodService {

    @Autowired
    private drinkFoodRepo eRepository;

    @Override
    public List<drink_food> list() {
        // TODO Auto-generated method stub
        return eRepository.findAll();
    }

}

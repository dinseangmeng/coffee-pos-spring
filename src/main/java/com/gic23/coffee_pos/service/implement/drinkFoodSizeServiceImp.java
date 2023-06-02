package com.gic23.coffee_pos.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gic23.coffee_pos.Repo.drinkFoodSizeRepo;
import com.gic23.coffee_pos.entity.drink_food_size;
import com.gic23.coffee_pos.service.drinkFoodSizeService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class drinkFoodSizeServiceImp implements drinkFoodSizeService {

    @Autowired
    drinkFoodSizeRepo eRepository;

    @Override
    public List<drink_food_size> list() {
        // TODO Auto-generated method stub
        return eRepository.findAll();
    }

}

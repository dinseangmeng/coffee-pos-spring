package com.gic23.coffee_pos.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gic23.coffee_pos.Repo.drinkFoodToppingRepo;
import com.gic23.coffee_pos.entity.drink_food_topping;
import com.gic23.coffee_pos.service.drinkFoodToppingService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class drinkFoodToppingServiceImp implements drinkFoodToppingService {
    @Autowired
    drinkFoodToppingRepo eRepository;

    @Override
    public List<drink_food_topping> list() {
        // TODO Auto-generated method stub
        return eRepository.findAll();
    }

    @Override
    public List<drink_food_topping> getByProductId(Integer productId) {
        // TODO Auto-generated method stub
        return eRepository.findByProductId(productId);
    }

    @Override
    public drink_food_topping getById(Integer id) {
        // TODO Auto-generated method stub
        return eRepository.findById(id).get();
    }

}

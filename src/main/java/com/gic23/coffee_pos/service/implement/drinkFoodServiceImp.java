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
        return eRepository.findAll();
    }

    @Override
    public List<drink_food> findByCategoryId(String categoryId) {
        return eRepository.findByCategoryCode(categoryId);
    }

    @Override
    public Long countByCategoryCode(String categoryId) {
        return eRepository.countByCategoryCode(categoryId);
    }

    @Override
    public Long countAll() {
        return eRepository.count();
    }

    @Override
    public List<drink_food> findBytypeId(Integer typeId) {
        return eRepository.findBytypeId(typeId);
    }

    @Override
    public Long countBytypeId(Integer typeId) {
        return eRepository.countBytypeId(typeId);
    }

    @Override
    public drink_food findById(Integer id) {
        // TODO Auto-generated method stub
        return eRepository.findById(id).get();
    }
}

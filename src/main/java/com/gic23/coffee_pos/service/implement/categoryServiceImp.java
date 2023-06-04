package com.gic23.coffee_pos.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gic23.coffee_pos.Repo.categoryRepo;
import com.gic23.coffee_pos.entity.category;
import com.gic23.coffee_pos.service.categoryService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.gic23.coffee_pos.dto.CategoryWithDrinkCount;

@Service
@Transactional
public class categoryServiceImp implements categoryService {

    @Autowired
    categoryRepo eRepository;

    @Autowired
    drinkFoodServiceImp drinkFoodService;

    @Override
    public List<CategoryWithDrinkCount> list() {
        List<category> categories = eRepository.findAll();
        List<CategoryWithDrinkCount> newObjects = new ArrayList<>();
        for (category item : categories) {
            newObjects
                    .add(
                            CategoryWithDrinkCount
                                    .builder()
                                    .category(item)
                                    .drink_count(drinkFoodService.countByCategoryCode(item.getCode()))
                                    .build());
        }
        return newObjects;
    }

    @Override
    public category getByCode(String code) {
        // TODO Auto-generated method stub
        return eRepository.findByCode(code);
    }

}

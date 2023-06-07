package com.gic23.coffee_pos.service;

import java.util.List;

import com.gic23.coffee_pos.entity.category;
import com.gic23.coffee_pos.dto.CategoryWithDrinkCount;

public interface categoryService {
    List<CategoryWithDrinkCount> list();

    category getByCode(String code);

    List<CategoryWithDrinkCount> findBytypeId(Integer typeId);

}

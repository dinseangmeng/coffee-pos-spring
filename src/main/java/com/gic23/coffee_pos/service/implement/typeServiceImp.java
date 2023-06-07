package com.gic23.coffee_pos.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gic23.coffee_pos.Repo.typeRepo;
import com.gic23.coffee_pos.entity.type;
import com.gic23.coffee_pos.service.typeService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class typeServiceImp implements typeService {

    @Autowired
    private typeRepo eRepository;

    @Override
    public List<type> list() {
        return eRepository.findAll();
    }

    @Override
    public type findById(Integer id) {
        return eRepository.findById(id).get();
    }
}

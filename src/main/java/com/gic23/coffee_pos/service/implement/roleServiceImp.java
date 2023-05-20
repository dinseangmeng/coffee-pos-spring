package com.gic23.coffee_pos.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gic23.coffee_pos.Repo.roleRepo;
import com.gic23.coffee_pos.entity.role;
import com.gic23.coffee_pos.service.roleService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class roleServiceImp implements roleService{
    
    @Autowired
    private roleRepo eRepository;

    @Override
    public List<role> list() {
        return eRepository.findAll();
    }

    @Override
    public role save(role role) {
        return eRepository.save(role);
    }
}

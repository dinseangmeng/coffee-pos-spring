package com.gic23.coffee_pos.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gic23.coffee_pos.Repo.userRepo;
import com.gic23.coffee_pos.entity.user;
import com.gic23.coffee_pos.service.userService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class userServiceImp implements userService {
    
    @Autowired
    private userRepo eRepository;
    
    @Override
    public List<user> list() {
        return eRepository.findAll();
    }
    
    @Override
    public user register(user user) {
        return eRepository.save(user);
    }

    @Override
    public List<user> listCasheir() {
        return eRepository.findByroleid(2);
    }
}

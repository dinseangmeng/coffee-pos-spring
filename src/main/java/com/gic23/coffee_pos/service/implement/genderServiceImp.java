package com.gic23.coffee_pos.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gic23.coffee_pos.Repo.genderRepo;
import com.gic23.coffee_pos.entity.gender;
import com.gic23.coffee_pos.service.genderService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class genderServiceImp implements genderService {

    @Autowired
    genderRepo eRepository;

    @Override
    public List<gender> list() {
        return eRepository.findAll();
    }

    @Override
    public gender save(gender gender) {

        return eRepository.save(gender);
    }
}

package com.gic23.coffee_pos.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gic23.coffee_pos.Repo.recieptRepo;
import com.gic23.coffee_pos.entity.reciept;
import com.gic23.coffee_pos.service.recieptService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class recieptServiceImp implements recieptService {

    @Autowired
    private recieptRepo eRepository;

    @Override
    public List<reciept> list() {
        return eRepository.findAll();
    }

    @Override
    public reciept findById(Integer id) {
        return eRepository.findById(id).get();
    }

    @Override
    public reciept findByorderId(Integer id) {
        // TODO Auto-generated method stub
        return eRepository.findByorderId(id);
    }

    @Override
    public reciept save(reciept reciept) {
        // TODO Auto-generated method stub
        return eRepository.save(reciept);
    }
}

package com.gic23.coffee_pos.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gic23.coffee_pos.Repo.tableRepo;
import com.gic23.coffee_pos.entity.tables;
import com.gic23.coffee_pos.service.tableService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class tableServiceImp implements tableService {

    @Autowired
    private tableRepo eRepository;

    @Override
    public List<tables> listTable() {
        // TODO Auto-generated method stub
        return eRepository.findAll();
    }

    @Override
    public Optional<tables> getById(Integer id) {
        // TODO Auto-generated method stub
        return eRepository.findById(id);
    }

    @Override
    public tables save(tables table) {
        // TODO Auto-generated method stub
        return eRepository.save(table);
    }
}

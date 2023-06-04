package com.gic23.coffee_pos.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gic23.coffee_pos.Repo.zoneRepo;
import com.gic23.coffee_pos.entity.zone;
import com.gic23.coffee_pos.service.zoneService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class zoneServiceImp implements zoneService {

    @Autowired
    zoneRepo eRepository;

    @Override
    public List<zone> list() {
        // TODO Auto-generated method stub
        return eRepository.findAll();
    }
}

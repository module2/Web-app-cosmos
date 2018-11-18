package com.codegym.service;

import com.codegym.model.Thing;
import com.codegym.repository.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ThingServiceImpl implements ThingService {

    @Autowired
    private ThingRepository thingRepository;

    @Override
    public Page<Thing> findAll(Pageable pageable) {
        return thingRepository.findAll(pageable);
    }

    @Override
    public Thing findById(Long id) {
        return thingRepository.findOne(id);
    }

    @Override
    public void save(Thing thing) {
        thingRepository.save(thing);
    }

    @Override
    public void remove(Long id) {
        thingRepository.delete(id);
    }
}

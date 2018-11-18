package com.codegym.service;

import com.codegym.model.Thing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ThingService {
    Page<Thing> findAll(Pageable pageable);

    Thing findById(Long id);

    void save(Thing thing);

    void remove(Long id);
}

package com.codegym.repository;

import com.codegym.model.Thing;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ThingRepository extends PagingAndSortingRepository<Thing, Long> {
}

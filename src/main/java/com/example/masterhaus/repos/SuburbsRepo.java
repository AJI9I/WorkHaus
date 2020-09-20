package com.example.masterhaus.repos;

import com.example.masterhaus.domain.Suburbs;
import org.springframework.data.repository.CrudRepository;

public interface SuburbsRepo extends CrudRepository<Suburbs, Long> {

    Suburbs findByName(String name);
}

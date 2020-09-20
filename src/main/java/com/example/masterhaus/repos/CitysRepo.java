package com.example.masterhaus.repos;

import com.example.masterhaus.domain.Citys;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CitysRepo extends CrudRepository<Citys,Long> {

    Citys findByName(String city);

}

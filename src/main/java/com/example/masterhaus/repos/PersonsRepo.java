package com.example.masterhaus.repos;

import com.example.masterhaus.domain.Persons;
import org.springframework.data.repository.CrudRepository;

public interface PersonsRepo extends CrudRepository<Persons, Long> {

    Persons findByPhone(String phone);
    Iterable<Persons> findAllByManagersId(Long mid);
}

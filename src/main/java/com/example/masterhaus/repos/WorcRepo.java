package com.example.masterhaus.repos;

import com.example.masterhaus.domain.Worcs;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface WorcRepo extends CrudRepository <Worcs, Long> {

    Optional<Worcs> findById(Long id);
    List<Worcs> findAllByPhone(String phone);
}

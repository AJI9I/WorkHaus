package com.example.masterhaus.repos;

import com.example.masterhaus.domain.Managers;
import com.example.masterhaus.domain.Worcs;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ManagersRepo extends CrudRepository<Managers,Long> {

    Managers findByPhone(String phone);

}

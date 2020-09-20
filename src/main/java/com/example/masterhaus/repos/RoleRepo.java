package com.example.masterhaus.repos;

import com.example.masterhaus.domain.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RoleRepo extends CrudRepository<Role,Long> {

    Set<Role> findByNamerole(String namerole);
}

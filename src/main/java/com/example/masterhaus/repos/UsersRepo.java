package com.example.masterhaus.repos;

import com.example.masterhaus.domain.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepo extends CrudRepository<Users,Long> {
    Users findByUsername(String username);
}

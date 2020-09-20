package com.example.masterhaus.repos;

import com.example.masterhaus.domain.Areas;
import org.springframework.data.repository.CrudRepository;

public interface AreasRepo extends CrudRepository<Areas,Long> {

    Areas findByAreasname(String areas);
}

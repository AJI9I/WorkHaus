package com.example.masterhaus.repos;

import com.example.masterhaus.domain.Categorys;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Optional;

public interface CategoryRepo extends CrudRepository<Categorys,Long> {



    Categorys findByName(String name);

    Optional<Categorys> findById(Long id);

    ArrayList<Categorys> findByLvl(Integer lvl);

    ArrayList<Categorys> findByFather(Integer father);

    @Modifying
    @Query("UPDATE Categorys c SET c.name=?1, c.father=?2, c.percent=?3, c.discription=?4 where c.id = ?5")
    void updateCat(String name, Integer father, double percent, String desk, Long id);



}

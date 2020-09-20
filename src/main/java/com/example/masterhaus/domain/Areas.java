package com.example.masterhaus.domain;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="areas")
public class Areas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 40)
    private String areasname;




    ///////////////////// Worcs worcs ////////////////////////
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "areas")
    private Set<Worcs> worcs;

    public Set<Worcs> getWorcs() {
        return worcs;
    }

    public void setWorcs(Set<Worcs> worcs) {
        this.worcs = worcs;
    }
///////////////////// Worcs worcs ////////////////////////


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAreasname() {
        return areasname;
    }

    public void setAreasname(String areasname) {
        this.areasname = areasname;
    }
}

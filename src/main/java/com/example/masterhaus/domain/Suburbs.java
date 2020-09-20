package com.example.masterhaus.domain;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="suburbs")
public class Suburbs {

    //Таблица пригороды
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 40)
    private String name;


    ///////////////////// Worcs worcs ////////////////////////
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "suburbs")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //    //Каждый пригород может относиться к одному городу
//    @ManyToOne
//    @JoinColumn(name = "citys_id", nullable = false)
//    private Citys citys;



//    @OneToOne(mappedBy = "suburbs")
//    private Citys citys;

    //каждый пригород относится ко многим работам
//    @ManyToOne
//    @JoinColumn(name = "suburbs_id", nullable = false)
//    private Worcs worcs;

//    //Каждый город может относиться ко многим пригородам
//    @OneToMany(mappedBy = "suburbs")
//    private Set<Worcs> worcs;


//    @OneToOne(mappedBy = "suburbs")
//    private Worcs worcs;

}

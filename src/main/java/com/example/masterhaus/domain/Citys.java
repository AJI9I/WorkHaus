package com.example.masterhaus.domain;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="citys")
public class Citys {

    //Таблица города

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 40)
    private String name;


    ///////////////////// Areas areas ////////////////////////

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Areas> areas = new ArrayList<>();

    public List<Areas> getAreas() {
        return areas;
    }

    public void setAreas(List<Areas> areas) {
        this.areas = areas;
    }

    ///////////////////// Areas areas ////////////////////////

    ///////////////////// Areas areas ////////////////////////

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Telegramuser> telegramusers = new ArrayList<>();

    public List<Telegramuser> getTelegramusers() {
        return telegramusers;
    }

    public void setTelegramusers(List<Telegramuser> telegramusers) {
        this.telegramusers = telegramusers;
    }
    ///////////////////// Areas areas ////////////////////////

    ///////////////////// Suburbs suburbs ////////////////////////
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Suburbs> suburbs = new ArrayList<>();

    public List<Suburbs> getSuburbs() {
        return suburbs;
    }

    public void setSuburbs(List<Suburbs> suburbs) {
        this.suburbs = suburbs;
    }

    ///////////////////// Suburbs suburbs ////////////////////////

    ///////////////////// Managers managers ////////////////////////
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "citys")
    private Set<Managers> managers;

    public Set<Managers> getManagers() {
        return managers;
    }

    public void setManagers(Set<Managers> managers) {
        this.managers = managers;
    }

    ///////////////////// Managers managers ////////////////////////

    ///////////////////// Messagefromsiteworc messagefromsiteworc ////////////////////////
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "citys")
    private List<Messagefromsiteworc> messagefromsiteworc;

    public List<Messagefromsiteworc> getMessagefromsiteworc() {
        return messagefromsiteworc;
    }

    public void setMessagefromsiteworc(List<Messagefromsiteworc> messagefromsiteworc) {
        this.messagefromsiteworc = messagefromsiteworc;
    }
///////////////////// Messagefromsiteworc messagefromsiteworc ////////////////////////

    ///////////////////// Personinterviewrequest personinterviewrequest ////////////////////////
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "citys")
    private Set<Personinterviewrequest> personinterviewrequest;

    public Set<Personinterviewrequest> getPersoninterviewrequest() {
        return personinterviewrequest;
    }

    public void setPersoninterviewrequest(Set<Personinterviewrequest> personinterviewrequest) {
        this.personinterviewrequest = personinterviewrequest;
    }
    ///////////////////// Personinterviewrequest personinterviewrequest ////////////////////////

    ///////////////////// Worcs worcs ////////////////////////
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "citys")
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


    //    // Каждый город относится ко многим работам
//    @OneToMany(mappedBy = "citys")
//    private Set<Worcs> worcs;
//
//    //Каждый город может относиться ко многим пригородам
//    @OneToMany(mappedBy = "citys")
//    private Set<Suburbs> suburbs;


//    @OneToOne( cascade = CascadeType.ALL)
//    @JoinTable(name = "citys_suburbs",
//            joinColumns = {@JoinColumn(name = "citys_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "citys_id",referencedColumnName = "id")})
//    private Suburbs suburbs;

}

package com.example.masterhaus.domain;

import javax.persistence.*;

@Entity
@Table(name="disput")
public class Disput {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 300)
    private String name;

    private boolean dispclosed =false;

    ///////////////////// Persons persons ////////////////////////
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "persons_id", nullable = false)
    private Persons persons;

    public Persons getPersons() {
        return persons;
    }

    public void setPersons(Persons persons) {
        this.persons = persons;
    }
    ///////////////////// Persons persons ////////////////////////


    ///////////////////// Worcs worcs ////////////////////////

    @OneToOne(mappedBy = "disput")
    private Worcs worcs;

    public Worcs getWorcs() {
        return worcs;
    }

    public void setWorcs(Worcs worcs) {
        this.worcs = worcs;
    }
    ///////////////////// Worcs worcs ////////////////////////

    ///////////////////// Managers managers ////////////////////////
    @OneToOne(mappedBy = "disput")
    private Managers managers;

    ///////////////////// Managers managers ////////////////////////



    public Disput() {
    }

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

    public boolean isDispclosed() {
        return dispclosed;
    }

    public void setDispclosed(boolean dispclosed) {
        this.dispclosed = dispclosed;
    }
}

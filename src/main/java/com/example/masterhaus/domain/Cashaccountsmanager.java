package com.example.masterhaus.domain;

import javax.persistence.*;

@Entity
@Table(name="cashaccountsmanager")
public class Cashaccountsmanager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int cashup = 0;

    private int cashdownperson=0;


//    //сashaccountsmanager
//    @OneToOne(mappedBy = "cashaccountsmanager")
//    private Managers managers;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCashup() {
        return cashup;
    }

    public void setCashup(int cashup) {
        this.cashup = cashup;
    }

    public int getCashdownperson() {
        return cashdownperson;
    }

    public void setCashdownperson(int cashdownperson) {
        this.cashdownperson = cashdownperson;
    }
}

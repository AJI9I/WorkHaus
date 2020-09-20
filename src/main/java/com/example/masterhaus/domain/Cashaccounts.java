package com.example.masterhaus.domain;

import javax.persistence.*;

@Entity
@Table(name="cashaccounts")
public class Cashaccounts {
    //Деньги конекретного человека
    //В поле cash  указывается последняя сумма на счету человека

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int cash = 0;


    ///////////////////// Persons persons ////////////////////////

    @OneToOne(mappedBy = "cashaccounts")
    private Persons persons;

    public Persons getPersons() {
        return persons;
    }

    public void setPersons(Persons persons) {
        this.persons = persons;
    }

    ///////////////////// Persons persons ////////////////////////


    public Cashaccounts() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }


//    @OneToOne(fetch= FetchType.LAZY,mappedBy = "cashaccounts")
//    private Persons persons;
}

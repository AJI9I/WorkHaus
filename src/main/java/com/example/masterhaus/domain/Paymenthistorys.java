package com.example.masterhaus.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name="paymenthistorys")
public class Paymenthistorys {

    // История движения средств
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate localdate = LocalDate.now();

    private int upcash;

    private int downcash;

    @Column(nullable = false, length = 50)
    private String operation;

    private int cash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persons_Id")
    private Persons persons;

    public Paymenthistorys() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLocaldate() {
        return localdate;
    }

    public void setLocaldate(LocalDate localdate) {
        this.localdate = localdate;
    }

    public int getUpcash() {
        return upcash;
    }

    public void setUpcash(int upcash) {
        this.upcash = upcash;
    }

    public int getDowncash() {
        return downcash;
    }

    public void setDowncash(int downcash) {
        this.downcash = downcash;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public Persons getPersons() {
        return persons;
    }

    public void setPersons(Persons persons) {
        this.persons = persons;
    }
}

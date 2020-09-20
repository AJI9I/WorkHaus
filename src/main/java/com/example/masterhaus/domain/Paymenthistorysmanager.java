package com.example.masterhaus.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="paymenthistorysmanager")
public class Paymenthistorysmanager {

    // История движения средств
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate localdate = LocalDate.now();

    private int upcash;

    private int downcash;

    @Column(nullable = false, length = 50)
    private String operation;

//    //Многим историям счетов принадлежит один пользователь
//    @ManyToOne
//    @JoinColumn(name = "managers_id", nullable = false)
//    private Managers managers;
}

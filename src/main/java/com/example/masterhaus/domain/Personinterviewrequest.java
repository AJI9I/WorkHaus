package com.example.masterhaus.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="personinterviewrequest")
public class Personinterviewrequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 500)
    private String message;
    @Column(nullable = false, length = 11)
    private String phone;
    @Column(nullable = false, length = 50)
    private String name;

    private boolean relevant;

    @Column(length = 100)
    private String comment;
    private LocalDate localdate = LocalDate.now();

    public Personinterviewrequest() {
    }

    public Personinterviewrequest(String message, String phone, String name, Citys citys, boolean relevant) {
        this.message = message;
        this.phone = phone;
        this.name = name;
        this.citys = citys;
        this.relevant = relevant;
    }

    ///////////////////// Citys citys ////////////////////////

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "citys_Id")
    private Citys citys;

    public Citys getCitys() {
        return citys;
    }

    public void setCitys(Citys citys) {
        this.citys = citys;
    }

    ///////////////////// Citys citys ////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRelevant() {
        return relevant;
    }

    public void setRelevant(boolean relevant) {
        this.relevant = relevant;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getLocaldate() {
        return localdate;
    }

    public void setLocaldate(LocalDate localdate) {
        this.localdate = localdate;
    }
}

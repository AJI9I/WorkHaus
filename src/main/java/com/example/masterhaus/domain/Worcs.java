package com.example.masterhaus.domain;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.apache.catalina.Manager;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name = "worcs")
public class Worcs{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 65)
    private String name;
    @Column(nullable = false, length = 300)
    private String fullname;

    private Double price;

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(nullable = false, length = 40)
    private String ron;
    @Column(nullable = false, length = 40)
    private String street;
    @Column(nullable = false, length = 10)
    private String house;
    @Column(nullable = false, length = 10)
    private String apartment;

    @Column(nullable = false, length = 40)
    private String fio;

    private boolean status = true;
    @Column(nullable = false, length = 30)
    private String statusname;

    private boolean confirmation;



    private LocalDate localdate = LocalDate.now();

    ////////////////Доработка//////////////////
    private String dispute;
    ///////////////////// Disput disput ////////////////////////
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name="worcs_disput",
            joinColumns =
                    {@JoinColumn(name = "worcs_id", referencedColumnName = "id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "disput_id", referencedColumnName = "id")})
    private Disput disput;

//    public boolean isDispute() {
//        return dispute;
//    }
//
//    public void setDispute(boolean dispute) {
//        this.dispute = dispute;
//    }

    public Disput getDisput() {
        return disput;
    }

    public void setDisput(Disput disput) {
        this.disput = disput;
    }
    ///////////////////// Disput disput ////////////////////////


    ////////////////Доработка//////////////////


    //много работ относится к одной категории
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "categorys_id", nullable = false)
    private Categorys categorys;


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

    ///////////////////// Areas areas ////////////////////////
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "areas_Id")
    private Areas areas;
    @Transactional
    public Areas getAreas() {
        return areas;
    }

    public void setAreas(Areas areas) {
        this.areas = areas;
    }
    ///////////////////// Areas areas ////////////////////////

    ///////////////////// Suburbs suburbs ////////////////////////
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "suburbs_id")
    private Suburbs suburbs;
    @Transactional
    public Suburbs getSuburbs() {
        return suburbs;
    }

    public void setSuburbs(Suburbs suburbs) {
        this.suburbs = suburbs;
    }

    ///////////////////// Suburbs suburbs ////////////////////////

    ///////////////////// Manager manager ////////////////////////

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "managers_Id")
    private Managers managers;

    public Managers getManagers() {
        return managers;
    }

    public void setManagers(Managers managers) {
        this.managers = managers;
    }

/////////// Manager manager ////////////////////////

    ///////////////////// Persons persons ////////////////////////
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "persons_id")
    private Persons persons;

    public Persons getPersons() {
        return persons;
    }

    public void setPersons(Persons persons) {
        this.persons = persons;
    }
    ///////////////////// Persons persons ////////////////////////

//    //Одна работа относится к одному работнику
//    @ManyToOne
//    @JoinColumn(name = "persons_id", nullable = false)
//    private Persons persons;


//    //Каждая работа относится к одному пригороду
//    @OneToOne( cascade = CascadeType.ALL)
//    @JoinTable(name = "works_suburbs",
//    joinColumns = {@JoinColumn(name = "worcs_id", referencedColumnName = "id")},
//    inverseJoinColumns = {@JoinColumn(name = "susburbs_id",referencedColumnName = "id")})
//    private Suburbs suburbs;



//    //Каждая работа относится к одному городу
//    @ManyToOne
//    @JoinColumn(name = "citys_id", nullable = false)
//    private Citys citys;



    public Worcs() {

    }

    public Worcs(String name, String fullname, String price, String phone, String ron, String street, String house, String apartment, String fio) {
        this.name = name;
        this.fullname = fullname;
        this.price = Double.valueOf(price);
        this.phone = phone;
        this.ron = ron;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.fio = fio;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRon() {
        return ron;
    }

    public void setRon(String ron) {
        this.ron = ron;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Categorys getCategorys() {
        return categorys;
    }

    public void setCategorys(Categorys categorys) {
        this.categorys = categorys;
    }

    public LocalDate getLocaldate() {
        return localdate;
    }

    public void setLocaldate(LocalDate localdate) {
        this.localdate = localdate;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    public String getDispute() {
        return dispute;
    }

    public void setDispute(String dispute) {
        this.dispute = dispute;
    }
}

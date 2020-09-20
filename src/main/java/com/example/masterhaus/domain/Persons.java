package com.example.masterhaus.domain;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="persons")
public class Persons {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 40)
    private String name;
    @Column(nullable = false, length = 40)
    private String surname;
    @Column(nullable = false, length = 40)
    private String family;
    @Column(nullable = false, length = 11)
    private String phone;

///////////////////// Cashaccounts cashaccounts ////////////////////////
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name="persons_cashaccounts",
    joinColumns =
            {@JoinColumn(name = "persons_id", referencedColumnName = "id")},
            inverseJoinColumns =
            {@JoinColumn(name = "cashaccounts_id", referencedColumnName = "id")})
    private Cashaccounts cashaccounts;

    public Cashaccounts getCashaccounts() {
        return cashaccounts;
    }

    public void setCashaccounts(Cashaccounts cashaccounts) {
        this.cashaccounts = cashaccounts;
    }

    ///////////////////// Cashaccounts cashaccounts ////////////////////////

    ///////////////////// Disput disput ////////////////////////
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "persons")
    private Set<Disput> disputs;
    ///////////////////// Disput disput ////////////////////////

    ///////////////////// Paymenthistorys paymenthistorys ////////////////////////
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "persons")
    private List<Paymenthistorys> paymenthistorys = new ArrayList<>();

    public List<Paymenthistorys> getPaymenthistorys() {
        return paymenthistorys;
    }

    public void setPaymenthistorys(List<Paymenthistorys> paymenthistorys) {
        this.paymenthistorys = paymenthistorys;
    }
    ///////////////////// Paymenthistorys paymenthistorys ////////////////////////

//    //Один профиль работника относится к одному менеджеру
//    @ManyToOne
//    @JoinColumn(name = "persons_id", nullable = false)
//    private Managers managers;

//    //Один профиль относится к одному счету
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id")
//    private Cashaccounts cashaccounts;

//    //Одному пользователю принадлежит много отчетов по счетам
//    //Каждый город может относиться ко многим пригородам
//    @OneToMany(mappedBy = "paymenthistorys")
//    private Set<Paymenthistorys> paymenthistorys;


//    // Работника относится ко многим работам
//    @OneToMany(mappedBy = "persons")
//    private Set<Worcs> worcs;

    ///////////////////// Telegramuser telegramuser ////////////////////////
    //Один профиль относится к одному чату
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "telegramuser", referencedColumnName = "id")
    private Telegramuser telegramuser;

    public Telegramuser getTelegramuser() {
        return telegramuser;
    }

    public void setTelegramuser(Telegramuser telegramuser) {
        this.telegramuser = telegramuser;
    }

    ///////////////////// Telegramuser telegramuser ////////////////////////

    ///////////////////// Managers managers ////////////////////////

    @ManyToOne
    @JoinColumn(name = "managers_id")
    private Managers managers;

    public Managers getManagers() {
        return managers;
    }

    public void setManagers(Managers managers) {
        this.managers = managers;
    }
    ///////////////////// Managers managers ////////////////////////


    ///////////////////// Worcs worcs ////////////////////////
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "persons")
    private Set<Worcs> worcs;

    public Set<Worcs> getWorcs() {
        return worcs;
    }

    public void setWorcs(Set<Worcs> worcs) {
        this.worcs = worcs;
    }
    ///////////////////// Worcs worcs ////////////////////////





    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "PersonCategoryWork",
        joinColumns = {
            @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false, updatable = false)},
        inverseJoinColumns = {
            @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, updatable = false)})
    private Set<Categorys> categorys = new HashSet<>();

    public Set<Categorys> getCategorys() {
        return categorys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persons persons = (Persons) o;
        return id.equals(persons.id) &&
                Objects.equals(name, persons.name) &&
                Objects.equals(surname, persons.surname) &&
                Objects.equals(family, persons.family) &&
                Objects.equals(phone, persons.phone) &&
                Objects.equals(telegramuser, persons.telegramuser) &&
                Objects.equals(categorys, persons.categorys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, family, phone, telegramuser, categorys);
    }

    public void setCategorys(Set<Categorys> categorys) {
        this.categorys = categorys;
    }
//    @OneToMany(fetch = FetchType.EAGER, mappedBy="persons")
//    private Set<PersonCategory> personCategories;

    public Persons(){}

    public Persons(String name, String surname, String family, String phone){
        this.name = name;
        this.surname = surname;
        this.family = family;
        this.phone = phone;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



//    public Set<PersonCategory> getPersonCategories() {
//        return personCategories;
//    }
//
//    public void setPersonCategories(Set<PersonCategory> personCategories) {
//        this.personCategories = personCategories;
//    }
}

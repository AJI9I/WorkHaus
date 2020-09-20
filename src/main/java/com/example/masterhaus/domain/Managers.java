package com.example.masterhaus.domain;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="managers")
public class Managers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 40)
    private String firstname;

    @Column(nullable = false, length = 40)
    private String lastname;

    @Column(nullable = false, length = 40)
    private String familyname;

    @Column(nullable = false, length = 11)
    private String phone;

    private boolean dostup;

    ///////////////////// Managers USER ////////////////////////

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name="managers_users",
            joinColumns =
                    {@JoinColumn(name = "managers_id", referencedColumnName = "id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "users_id", referencedColumnName = "id")})
    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    ///////////////////// Cashaccounts cashaccounts ////////////////////////

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

    ///////////////////// Disput disput ////////////////////////
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name="managers_disput",
            joinColumns =
                    {@JoinColumn(name = "managers_id", referencedColumnName = "id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "disput_id", referencedColumnName = "id")})
    private Disput disput;

    public Disput getDisput() {
        return disput;
    }

    public void setDisput(Disput disput) {
        this.disput = disput;
    }
    ///////////////////// Disput disput ////////////////////////

    ///////////////////// Persons persons ////////////////////////
    @OneToMany(mappedBy = "managers")
    private Set<Persons> persons;

    public Set<Persons> getPersons() {
        return persons;
    }

    public void setPersons(Set<Persons> persons) {
        this.persons = persons;
    }
    ///////////////////// Persons persons ////////////////////////

    ///////////////////// Messagefromsiteworc messagefromsiteworc ////////////////////////
    @OneToMany(mappedBy = "managers")
    private List<Messagefromsiteworc> messagefromsiteworc;

    public List<Messagefromsiteworc> getMessagefromsiteworc() {
        return messagefromsiteworc;
    }

    public void setMessagefromsiteworc(List<Messagefromsiteworc> messagefromsiteworc) {
        this.messagefromsiteworc = messagefromsiteworc;
    }
    ///////////////////// Messagefromsiteworc messagefromsiteworc ////////////////////////




    ///////////////////// Worcs worcs ////////////////////////

        @OneToMany(fetch = FetchType.EAGER, mappedBy = "managers")
        private Set<Worcs> worcs = new HashSet<Worcs>();

        public Set<Worcs> getWorcs() {
            return worcs;
        }

        public void setWorcs(Set<Worcs> worcs) {
            this.worcs = worcs;
        }

    ///////////////////// Worcs worcs ////////////////////////






//    //Каждый менеджер имеет личный кошелек
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinTable(name = "manager_сashaccountsmanager",
//    joinColumns =
//            {@JoinColumn(name="manager_id", referencedColumnName = "id")},
//    inverseJoinColumns =
//            {@JoinColumn(name = "cashaccountsmanager_id", referencedColumnName = "id")})
//    private Cashaccountsmanager cashaccountsmanager;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isDostup() {
        return dostup;
    }

    public void setDostup(boolean dostup) {
        this.dostup = dostup;
    }

    //    @OneToMany(mappedBy = "managers")
//    private Set<Paymenthistorysmanager> paymenthistorysmanagers;
//
//
//    @OneToMany(mappedBy = "persons")
//    private Set<Paymenthistorys> paymenthistorys;
}

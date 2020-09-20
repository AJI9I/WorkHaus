package com.example.masterhaus.domain;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Table(name="telegramuser")
public class Telegramuser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 30)
    private String chatid;

    private boolean autoreise;

    @Column(length = 30)
    private String tgname;
    ///////////////////// Persons persons ////////////////////////

    //Один чат телеграмма может относиться к одному аккаунту работника
    @OneToOne(mappedBy = "telegramuser")
    private Persons persons;

    public Persons getPersons() {
        return persons;
    }

    public void setPersons(Persons persons) {
        this.persons = persons;
    }

    ///////////////////// Persons persons ////////////////////////

    ///////////////////// Citys citys ////////////////////////
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "citys_id")
    private Citys citys;

    public Citys getCitys() {
        return citys;
    }

    public void setCitys(Citys citys) {
        this.citys = citys;
    }
    ///////////////////// Citys citys ////////////////////////

    ///////////////////// Persons persons ////////////////////////

    //Один чат телеграмма может относиться к одному аккаунту работника
    @OneToOne(mappedBy = "telegramuser")
    private Managers managers;

    public Managers getManagers() {
        return managers;
    }

    public void setManagers(Managers managers) {
        this.managers = managers;
    }
    ///////////////////// Persons persons ////////////////////////

    public String getTgname() {
        return tgname;
    }

    public void setTgname(String tgname) {
        this.tgname = tgname;
    }

    public Telegramuser() {
    }

    public Telegramuser(String chatid, boolean autoreise) {
        this.chatid = chatid;
        this.autoreise = autoreise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid;
    }

    public boolean isAutoreise() {
        return autoreise;
    }

    public void setAutoreise(boolean autoreise) {
        this.autoreise = autoreise;
    }
}

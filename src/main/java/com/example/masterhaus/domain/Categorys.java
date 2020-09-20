package com.example.masterhaus.domain;



import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="categorys")
public class Categorys {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 65)
    private String name;

    @Column(length = 300)
    private String discription;

    private Integer father;
    private Integer lvl;

    private double percent;

    @ManyToMany(mappedBy = "categorys", fetch = FetchType.LAZY)
    private Set<Persons> persons = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "categorys")
    private Set<Worcs> worcs;

    public Set<Worcs> getWorcs() {
        return worcs;
    }

    public void setWorcs(Set<Worcs> worcs) {
        this.worcs = worcs;
    }

    public Categorys(){

    }
    public Categorys(String CategoryFather, String CategoryName, String CategoryPercent, String CategoryDescription)
    {
        if (CategoryFather == "")
        {
            this.father = 0;
            this.lvl = 0;
        }
        else {
            this.father = Integer.valueOf(CategoryFather);
            this.lvl = 1;
        }

     this.name = CategoryName;
     this.percent = Double.valueOf(CategoryPercent);
     this.discription = CategoryDescription;
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

    public Integer getFather() {
        return father;
    }

    public void setFather(Integer father) {
        this.father = father;
    }

    public Integer getLvl() {
        return lvl;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }
    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Set<Persons> getPersons() {
        return persons;
    }

    public void setPersons(Set<Persons> persons) {
        this.persons = persons;
    }


}

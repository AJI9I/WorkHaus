package com.example.masterhaus.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="usr")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    private String username;
    @Column(nullable = false, length = 100)
    private  String password;
    private  boolean active;

    @Column(nullable = false, length = 100)
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    ///////////////////// Persons persons ////////////////////////

    @OneToOne(mappedBy = "users")
    private Managers managers;

    public Managers getManagers() {
        return managers;
    }

    public void setManagers(Managers managers) {
        this.managers = managers;
    }

    ///////////////////// Persons persons ////////////////////////

    ///////////////////// Roles  ////////////////////////

    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "usr_roles",
//            joinColumns = @JoinColumn(name = "users_id"),
//            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

    ///////////////////// Roles  ////////////////////////


    public boolean getIsAdmin(){
        if(roles.stream().anyMatch(p->p.getNamerole().equals("ROLE_ADMIN"))){
            return true;
        }
        return false;
    }
    public boolean getIsManag(){
        if(roles.stream().anyMatch(p->p.getNamerole().equals("ROLE_MANAG"))){
            return true;
        }
        return false;
    }

    public Users() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

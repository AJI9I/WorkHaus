package com.example.masterhaus.service;

import com.example.masterhaus.domain.Role;
import com.example.masterhaus.domain.Users;
import com.example.masterhaus.repos.RoleRepo;
import com.example.masterhaus.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UsersRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(s);
        if(user == null){
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return user;
    }

    public String getPhoneRegx(String phone){
        return phone.replace("/","")
                .replace("'","")
                .replace("\"","")
                .replace("=","")
                .replace("\\","")
                .replace("(","")
                .replace(")","")
                .replace(" ","")
                .replace("-","")
                .replace("+","");
    }

    public Users findUserById(Long id){
        return userRepo.findById(id).get();
    }

    public boolean saveUser(Users u){
        String uu = u.getUsername();
        Users user = userRepo.findByUsername(uu);

        if(user != null){
            return false;
        }

        user = new Users();
        user.setKey(u.getPassword());
        user.setUsername(u.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
        user.setActive(true);
        userRepo.save(user);

        user = userRepo.findByUsername(uu);
        Set<Role> role = roleRepo.findByNamerole("ROLE_MANAG");
        user.setRoles(role);
        if(role==null){
            return false;
        }
        userRepo.save(user);

        return true;
    }

    public Users findUsersByName(String name){
        return userRepo.findByUsername(name);
    }

    public boolean deleteUser(Long id){
        if(userRepo.findById(id).isPresent()){
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }
}

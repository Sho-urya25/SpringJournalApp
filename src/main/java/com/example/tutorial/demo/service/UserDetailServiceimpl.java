package com.example.tutorial.demo.service;

import com.example.tutorial.demo.entity.UserEntity;
import com.example.tutorial.demo.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
 public class UserDetailServiceimpl implements UserDetailsService {
    @Autowired
    private UserEntityRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.getUserByUserName(username);
        if (user != null){
            UserDetails userdetail =  User.builder().username(user.getUserName()).password(user.getPassword()).roles(user.getRoles().toArray(new String [0])).build();
        return  userdetail;
        }
        throw  new UsernameNotFoundException("Username not found"+username);
    }
}

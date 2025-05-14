package com.example.tutorial.demo.service;

import com.example.tutorial.demo.entity.JourEntity;
import com.example.tutorial.demo.entity.UserEntity;
import com.example.tutorial.demo.repository.UserEntityRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Component
public class UserEntityService {
    @Autowired
    private UserEntityRepository userEntityRepo;
    PasswordEncoder pass = new BCryptPasswordEncoder();

    public void createUser(UserEntity userentity) {
        userentity.setPassword(pass.encode(userentity.getPassword()));
        userentity.setRoles(List.of("USER"));
        userEntityRepo.save(userentity);
    }
    public  void saveUser(UserEntity user){
        userEntityRepo.save(user);
    }
    public List<UserEntity> getAllUser() {
        return userEntityRepo.findAll();
    }
    public void deleteUser(String id) {
        ObjectId obId = new ObjectId(id);
        userEntityRepo.deleteById(obId);
    }
    public void updateUserEntity(UserEntity userEntity) {

        userEntityRepo.findById(userEntity.getId()).ifPresent(existingEntry -> {
            existingEntry.setUserName(userEntity.getUserName());
            existingEntry.setPassword(userEntity.getPassword());
            userEntityRepo.save(existingEntry);
        });
    }
    public void deleteAll(){
        userEntityRepo.deleteAll();
    }
    public UserEntity findByUserName(String userName) {
        return userEntityRepo.getUserByUserName(userName);
    }

}
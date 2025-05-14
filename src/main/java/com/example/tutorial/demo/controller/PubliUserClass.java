package com.example.tutorial.demo.controller;

import com.example.tutorial.demo.entity.UserEntity;
import com.example.tutorial.demo.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/user")
public class PubliUserClass {
    @Autowired
    private UserEntityService userEntityService;
    @PostMapping
    public ResponseEntity<String> createJournalEntry(@RequestBody UserEntity userEntry) {
        userEntityService.createUser(userEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created Sucessfully ");
    }
    @GetMapping
    public List<UserEntity> getAllUser() {
        return userEntityService.getAllUser();
    }
}

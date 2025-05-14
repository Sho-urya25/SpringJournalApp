package com.example.tutorial.demo.controller;
import com.example.tutorial.demo.entity.UserEntity;
import com.example.tutorial.demo.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserEntityController {
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
   @PutMapping
   public String updateUserPass(@RequestBody UserEntity entity) {
        UserEntity userInDb = userEntityService.findByUserName(entity.getUserName());
        if(userInDb != null){
            userInDb.setUserName(entity.getUserName());
            userInDb.setPassword(entity.getPassword());
        }
       
       return "Updated succesfully";
   }
   @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        userEntityService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All user entries deleted successfully");
    }
   @GetMapping("/{username}")
    public UserEntity getUserByUsername(@PathVariable String username){
        return userEntityService.findByUserName(username);
   }
    
}


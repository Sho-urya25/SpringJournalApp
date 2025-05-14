package com.example.tutorial.demo.controller;
import java.util.List;

import com.example.tutorial.demo.entity.UserEntity;
import com.example.tutorial.demo.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tutorial.demo.entity.JourEntity;
import com.example.tutorial.demo.service.JournalEntrySecvice;



@RestController
@RequestMapping("/user/journal")
public class JourEntryconteroler {
    @Autowired
    private JournalEntrySecvice journalEntrySecvice;
    @Autowired
    private UserEntityService userEntityService;

    @PostMapping("/create")
    public ResponseEntity<String> createJournalEntry(@RequestBody JourEntity journalEntry) {
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = authUser.getName();
      journalEntrySecvice.createJournalEntry(journalEntry,userName);
        return ResponseEntity.status(HttpStatus.CREATED).body("Journal entry created successfully");
    }
   @GetMapping("/getAllJournalEntries")
   public List<JourEntity> getMethodName() {
       Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
       String userName = authUser.getName();
       UserEntity userEntity = userEntityService.findByUserName(userName);
       return journalEntrySecvice.getAllUserJournalEntries(userName);
   }
   @PutMapping("/{id}")
   public String putMethodName(@PathVariable String id, @RequestBody JourEntity entity) {
       Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
       String userName = authUser.getName();
        journalEntrySecvice.updateJournalEntry(entity,id,userName);
       return "Updated successfully";
   }
   @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
       Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
       String userName = authUser.getName();
        journalEntrySecvice.deleteAllUserJournal(userName);
        return ResponseEntity.status(HttpStatus.OK).body("All journal entries deleted successfully");
    }
}


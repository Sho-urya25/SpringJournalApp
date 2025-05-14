package com.example.tutorial.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.tutorial.demo.entity.UserEntity;
import com.example.tutorial.demo.repository.UserEntityRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.tutorial.demo.entity.JourEntity;
import com.example.tutorial.demo.repository.JournalEntityRepository;
@Component
public class JournalEntrySecvice {
    @Autowired
    private JournalEntityRepository journalEntityRepository;
@Autowired
private UserEntityService userEntityService;
    public void createJournalEntry(JourEntity journalEntry ,String userName) {
       try {
           UserEntity user = userEntityService.findByUserName(userName);
           if(user != null){
               JourEntity saved =  journalEntityRepository.save(journalEntry);
               user.getJournalEntries().add(saved.getId().toString());
              userEntityService.saveUser(user);
           }
       }catch ( Exception e){
           throw  new RuntimeException("New run time excetion "+ e);
       }

    }
    public List<JourEntity> getAllUserJournalEntries(String userName) {
        UserEntity user = userEntityService.findByUserName(userName);
        List<String> userJournalIds = user.getJournalEntries();
        List<JourEntity> jourEntities = journalEntityRepository.findAll();
        return jourEntities.stream().filter(element->userJournalIds.contains(element.getId().toString())).collect(Collectors.toList());
    }
    public void deleteJournalEntry(String id,String userName) {
        UserEntity user = userEntityService.findByUserName(userName);
        List<String> userJournalIds = user.getJournalEntries();
        userJournalIds.remove(id);
        user.setJournalEntries(userJournalIds);
        userEntityService.saveUser(user);
        journalEntityRepository.deleteById(id);
    }
    public void updateJournalEntry(JourEntity journalEntry, String id,String userName) {
        UserEntity user = userEntityService.findByUserName(userName);
        if (user.getJournalEntries().contains(id)) {
            journalEntityRepository.findById(id).ifPresent(existingEntry -> {
                existingEntry.setTitle(journalEntry.getTitle());
                existingEntry.setContent(journalEntry.getContent());
                journalEntityRepository.save(existingEntry);
            });
        }

    }
    public void deleteAllUserJournal(String userName){
        UserEntity user = userEntityService.findByUserName(userName);
        user.getJournalEntries().forEach(id -> {
            journalEntityRepository.deleteById(id); // preferred for MongoDB ObjectId
        });
        user.setJournalEntries(Collections.emptyList());
        userEntityService.saveUser(user);
    }

}
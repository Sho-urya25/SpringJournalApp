package com.example.tutorial.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.example.tutorial.demo.entity.JourEntity;

@Component
public interface JournalEntityRepository extends MongoRepository<JourEntity,String> {

}

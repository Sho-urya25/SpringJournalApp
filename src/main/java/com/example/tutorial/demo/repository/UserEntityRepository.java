package com.example.tutorial.demo.repository;

import com.example.tutorial.demo.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.rmi.server.ObjID;

public interface UserEntityRepository extends MongoRepository<UserEntity, ObjectId> {
    UserEntity getUserByUserName(String userName);
}

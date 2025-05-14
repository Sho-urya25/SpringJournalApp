package com.example.tutorial.demo.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class UserEntity {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private  String userName ;
    @NonNull
    private  String password;
    private List<String> journalEntries= new ArrayList<>();
    private List<String> roles;
}

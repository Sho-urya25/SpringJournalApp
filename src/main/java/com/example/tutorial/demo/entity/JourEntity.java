package com.example.tutorial.demo.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;



@Data

@Document(collection = "jour")

public class JourEntity {
    private ObjectId id;
    private String title;
    private String content;
  

}

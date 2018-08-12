 package com.mondiamedia.cleanup.kissapi.entity;

 import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;

 import org.springframework.data.mongodb.core.mapping.Document;

 import lombok.Getter;
 import lombok.Setter;

 @Setter
 @Getter
 @Document(collection = "Kiss-API")
 public class KissAPI {

 /** Play List id. */
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Id
 private String id;

 private String owner;

 public void setOwner(final String userId) {
 this.owner = userId;

 }

 }

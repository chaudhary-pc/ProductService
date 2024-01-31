package org.example.productservice.inheritancedemo.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="joined_instructor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Instructor extends User{
    private String teachingDomain;
}

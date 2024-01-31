package org.example.productservice.inheritancedemo.tableperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name= "tpc_instructor")
public class Instructor extends User {
    private String teachingDomain;
}

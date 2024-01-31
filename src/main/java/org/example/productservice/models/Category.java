package org.example.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    // cascade remove means if category got removed then removed the product as well
    // It is by default lazy fetch bcz it's a collection, we can change to Eager by mentioning fetchType
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = CascadeType.REMOVE)  //being already mapped by a attribute called category in other class
     List<Product> products;
    private String name;
}

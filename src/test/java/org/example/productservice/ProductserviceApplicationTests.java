package org.example.productservice;

import org.example.productservice.repositories.ProductRepository;
import org.example.productservice.repositories.projections.ProductWithIdAndTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductserviceApplicationTests {
    @Autowired
    ProductRepository productRepository;
    @Test
    void contextLoads() {
    }

    @Test
    void testQueries(){
        List<ProductWithIdAndTitle> products = productRepository.getIdAndTitleFromProduct(102L);
        for(ProductWithIdAndTitle product : products){
            System.out.println(product.getId());
            System.out.println(product.getTitle());
        }
    }
}

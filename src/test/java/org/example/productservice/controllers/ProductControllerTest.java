package org.example.productservice.controllers;
//
//import org.apache.coyote.Response;
//import org.example.productservice.exceptions.ProductNotExistException;
//import org.example.productservice.models.Product;
//import org.example.productservice.repositories.ProductRepository;
//import org.example.productservice.services.ProductService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
////
//@SpringBootTest
//class ProductControllerTest {
//
//    @Autowired
//    ProductController productController;
//    @MockBean
//    ProductService productService;
//    @MockBean
//    ProductRepository productRepository;
//    @Test
//    void testGetAllProducts() {
//        // check the products size and title return by service and controller should be same
//        List<Product> products = new ArrayList<>();
//
//        Product p1 = new Product();
//        p1.setTitle("Iphone 15 Pro");
//        p1.setPrice(140000.0);
//        products.add(p1);
//
//        Product p2 = new Product();
//        p2.setTitle("Iphone 14 Pro");
//        p2.setPrice(120000.0);
//        products.add(p2);
//
//        List<Product> products_copy = new ArrayList<>();
//        for(Product prod: products){
//            Product p = new Product();
//            p.setTitle(prod.getTitle());
//            p.setPrice(prod.getPrice());
//            products_copy.add(p);
//        }
//
//        // arrange
//        when(
//                productService.getAllProducts()
//        ).thenReturn(
//                products_copy
//        );
//
//        //act
//        ResponseEntity<List<Product>> productsInResponse = productController.getAllProducts();
//
//        //assert
//        assertEquals(products.size() , productsInResponse.getBody().size());
//
//        for(int i=0;i<products.size();i++){
//            assertEquals(products.get(i).getTitle(), productsInResponse.getBody().get(i).getTitle());
//        }
//    }
//
//    @Test
//    void testProductNotExistException()  {
//        //arrange
//        when(
//                productRepository.findById(200L)
//        ).thenReturn(
//                Optional.empty()
//        );
//
//        //act
//        assertThrows(
//                ProductNotExistException.class,
//                ()-> productController.getSingleProduct(200L)
//        );
//    }
//}
package org.example.productservice.controllers;

import org.example.productservice.commons.AuthenticationCommons;
import org.example.productservice.exceptions.CategoryNotExistException;
import org.example.productservice.exceptions.ProductNotExistException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private AuthenticationCommons authenticationCommons;
    @Autowired
    public ProductController(@Qualifier("FakeStoreProductService") ProductService productService, AuthenticationCommons authenticationCommons){
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
//        for Testing the test case
//        List<Product> products =  productService.getAllProducts();
//        List<Product> result = new ArrayList<>();
//        for(Product prod: products){
//            prod.setTitle("Hey "+prod.getTitle());
//            result.add(prod);
//        }
//        return result;

//        if(authenticationCommons.validateToken(token) == null){
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotExistException {
        return new ResponseEntity<>(productService.getSingleProduct(id) , HttpStatus.OK);
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product);
    }

    @PatchMapping("{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotExistException{
        return productService.updateProduct(id, product);
    }
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return productService.getAllCategories();
    }
    @GetMapping("/category/{name}")
    public Category getCategory(@PathVariable("name") String name) throws CategoryNotExistException {
        return productService.getCategory(name);
    }
}

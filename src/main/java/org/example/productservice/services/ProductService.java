package org.example.productservice.services;

import org.example.productservice.exceptions.CategoryNotExistException;
import org.example.productservice.exceptions.ProductNotExistException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getSingleProduct(Long id) throws ProductNotExistException;
    Product replaceProduct(Long id, Product product);
    Product updateProduct(Long id, Product product) throws ProductNotExistException;
    Product addNewProduct(Product product);
    void deleteProduct(Long id);
    List<Category> getAllCategories();
    Category getCategory(String name) throws CategoryNotExistException;
}

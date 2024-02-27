package org.example.productservice.services;

import org.example.productservice.exceptions.CategoryNotExistException;
import org.example.productservice.exceptions.ProductNotExistException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.repositories.CategoryRepository;
import org.example.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service("SelfProductService")
@Primary
//now this class gets priority and spring will return selfProductService object
//but if we use primary in more than 2 classes then again same issue so use Qualifier
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    @Autowired
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistException{
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()) {
            throw new ProductNotExistException("Product with id " + id + " doesn't exist!");
        }
        return productOptional.get();
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotExistException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotExistException("Product with id "+id+" doesn't exist for update!");
        }
        Product savedProduct = optionalProduct.get();
        if(product.getTitle() != null){
            savedProduct.setTitle(product.getTitle());
        }
        if(product.getPrice() != null){
            savedProduct.setPrice(product.getPrice());
        }
        if(product.getDescription() != null){
            savedProduct.setDescription(product.getDescription());
        }
        if(product.getImage() != null){
            savedProduct.setImage(product.getImage());
        }
        return productRepository.save(savedProduct);
    }

    @Override
    public Product addNewProduct(Product product) {
        //check if the category name already presents in category table, if yes then use same category else create it.
        Optional<Category> optionalCategory = categoryRepository.findByName(product.getCategory().getName());
        if(optionalCategory.isEmpty()) {
            //commented bcz i added Cascade so no need to add below line
//            product.setCategory(categoryRepository.save(product.getCategory()));
        }else{
            product.setCategory(optionalCategory.get());
        }
        return productRepository.save(product);
    }


    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(String name) throws CategoryNotExistException {
        Optional<Category> optionalCategory = categoryRepository.findByName(name);
        if(optionalCategory.isEmpty()){
            throw new CategoryNotExistException("Category with name "+name+" doesn't exist!!");
        }
        return optionalCategory.get();
    }
}

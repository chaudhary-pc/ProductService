package org.example.productservice.services;

import org.example.productservice.dtos.FakeStoreProductDto;
import org.example.productservice.exceptions.ProductNotExistException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;
    @Autowired
    FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public Product convertFakeStoreProductToProduct(FakeStoreProductDto responseDto){
        Product product = new Product();
        product.setId(responseDto.getId());
        product.setTitle(responseDto.getTitle());
        product.setPrice(responseDto.getPrice());
        product.setDescription(responseDto.getDescription());
        product.setImage(responseDto.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(responseDto.getCategory());
        return product;
    }
    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] responseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        List<Product> result = new ArrayList<>();
        for(FakeStoreProductDto productDto : responseDto){
            result.add(convertFakeStoreProductToProduct(productDto));
        }
        return result;
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistException {
//        int a = 1/0;
        FakeStoreProductDto responseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id,
                FakeStoreProductDto.class
        );
        if(responseDto == null){
            throw new ProductNotExistException("Product with id "+id+" doesn't exist!!");
        }
        return convertFakeStoreProductToProduct(responseDto);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(new FakeStoreProductDto(), FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products", HttpMethod.PATCH, requestCallback, responseExtractor);
        return convertFakeStoreProductToProduct(response);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        return;
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public Category getCategory(String name) {
        return null;
    }
}

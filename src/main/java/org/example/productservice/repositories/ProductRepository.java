package org.example.productservice.repositories;

import org.example.productservice.models.Product;
import org.example.productservice.repositories.projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    Product save(Product product);

    List<Product> findByTitleAndDescription(String title, String description);
    List<Product> findByPriceBetween(double startRange, double endRange);

    List<Product> findByCategory_Id(Long id);

    //HQL Queries
    @Query("SELECT p.id as id, p.title as title from Product p where p.id = :id")
    List<ProductWithIdAndTitle> getIdAndTitleFromProduct(@Param("id") Long id);

    //Native query
    @Query(value = "select p.id as id, p.title as title from product p where p.id = 60", nativeQuery = true)
    List<ProductWithIdAndTitle> getIdAndTitleFromProduct();
}

package com.teksystems.database.dao;

import com.teksystems.database.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Long> {

    //get all users - JPA hibernate
    @Query("From Product p")
    List<Product> getAllProducts();

    //get user by id - JPA hibernate
    @Query("From Product p where p.id = :id")
    Product findById(Integer id);

    //get product matching search
    @Query(value="select * from products p where lower(p.name) like lower(concat('%', :search, '%')) or lower(p.product_type) like lower(concat('%', :search, '%')) ;", nativeQuery = true)
    List<Product> findProductsBySearch(String search);


}

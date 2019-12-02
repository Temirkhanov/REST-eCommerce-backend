package com.goruslan.eCommerce.dao;


import com.goruslan.eCommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {



}

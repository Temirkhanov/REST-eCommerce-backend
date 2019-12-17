package com.goruslan.eCommerce.controller;

import com.goruslan.eCommerce.entity.Product;
import com.goruslan.eCommerce.exception.ProductNotFoundException;
import com.goruslan.eCommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Optional<Product> getProduct(@PathVariable long id) {
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            throw new ProductNotFoundException("id - " + id);
        }
        else return product;

    }

}

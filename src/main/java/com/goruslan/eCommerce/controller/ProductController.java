package com.goruslan.eCommerce.controller;

import com.goruslan.eCommerce.entity.Product;
import com.goruslan.eCommerce.exception.NotFoundException;
import com.goruslan.eCommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // All Products
    @GetMapping("/products")
    public MappingJacksonValue getAllProducts(){
        return ProductFilter.filterProductList(productRepository.findAll(), "id", "name", "unitPrice", "imageUrl");
    }

    // Specific Product
    @GetMapping("/products/{id}")
    public MappingJacksonValue getProduct(@PathVariable long id) {
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            throw new NotFoundException("id - " + id);
        }
        return ProductFilter.filterProduct(product.get(), "id", "name", "unitPrice", "imageUrl", "description", "unitPrice", "unitsInStock");
    }


}

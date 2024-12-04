package com.example.e_commerce.services;

import com.example.e_commerce.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getProductById(Integer id);

    List<Product> searchProductsByName(String name);

    List<Product> filterProductsByPrice(Double minPrice, Double maxPrice);

    Product saveProduct(Product product);

    void deleteProduct(Integer id);
}


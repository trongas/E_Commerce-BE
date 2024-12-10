package com.example.e_commerce.services;

import com.example.e_commerce.dto.request.ProductRequest;
import com.example.e_commerce.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;

public interface ProductService {
    Page<ProductResponse> getAllProducts(String name, Boolean status, int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection);

    ProductResponse getProductById(Integer id);

    Page<ProductResponse> searchProductsByName(String name, int pageNumber, int pageSize);

    Page<ProductResponse> filterProductsByPrice(BigDecimal minPrice, BigDecimal maxPrice, int pageNumber, int pageSize);

    ProductResponse saveProduct(ProductRequest productRequest) ;

    ProductResponse updateProduct(Integer productId, ProductRequest productRequest);

    ProductResponse deleteProduct(Integer id);
}

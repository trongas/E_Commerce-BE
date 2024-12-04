package com.example.e_commerce.repository;

import com.example.e_commerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Tìm kiếm sản phẩm theo tên (không phân biệt hoa/thường)
    List<Product> findByProductNameContainingIgnoreCase(String name);

    // Lọc sản phẩm theo giá
    List<Product> findByUnitPriceBetween(Double minPrice, Double maxPrice);

}

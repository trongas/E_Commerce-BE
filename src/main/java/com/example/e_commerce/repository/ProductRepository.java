package com.example.e_commerce.repository;

import com.example.e_commerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p.productId, p.productName, pp.pictureUrl " +
            "FROM Product p " +
            "LEFT JOIN ProductPicture pp ON p.productId = pp.product.productId")
    List<Object[]> findProductsWithPictures();

    // Tìm kiếm sản phẩm theo tên với phân trang
    Page<Product> findByProductNameContainingIgnoreCase(String name, Pageable pageable);

    // Lọc sản phẩm theo khoảng giá
    Page<Product> findByUnitPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);


}

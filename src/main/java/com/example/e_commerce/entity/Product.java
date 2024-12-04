package com.example.e_commerce.entity;

import com.example.e_commerce.enums.Availability;
import com.example.e_commerce.enums.DiscountAvailability;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(nullable = false, length = 255)
    private String productName;

    @Lob
    private String productDesc;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "supplierId")
    private Suppliers supplier;

    @Column(nullable = false)
    private Integer quantity;

    @Column(length = 50)
    private String size;

    @Column(precision = 8, scale = 3)
    private BigDecimal unitWeight;

    private String picture;

    @Column(length = 45)
    private String currentOrder;

    @Column(length = 45)
    private String idSku;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private Integer unitsInStock;

    private Integer unitsOnOrder;

    @Enumerated(EnumType.STRING)
    @Column(length = 3, columnDefinition = "ENUM('Yes', 'No') DEFAULT 'Yes'")
    private Availability productAvailable;

    @Enumerated(EnumType.STRING)
    @Column(length = 3, columnDefinition = "ENUM('Yes', 'No') DEFAULT 'No'")
    private DiscountAvailability discountAvailable;

    @Column(nullable = false)
    @Min(1)
    @Max(5)
    private Integer rating;


}




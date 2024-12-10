package com.example.e_commerce.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductResponse {
    private Integer productId;
    private String productName;
    private String productDesc;
    private String categoryName;
    private String supplierName;
    private Integer quantity;
    private String size;
    private BigDecimal unitWeight;

    private String currentOrder;
    private String idSku;
    private BigDecimal unitPrice;
    private Integer unitsInStock;
    private Integer unitsOnOrder;
    private String productAvailable;
    private String discountAvailable;
    private Integer rating;
    private List<ProductPictureResponse> pictures;
}

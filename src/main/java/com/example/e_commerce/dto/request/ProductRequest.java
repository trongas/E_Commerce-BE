package com.example.e_commerce.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductRequest {
    @NotBlank
    private String productName;

    private String productDesc;

    private Integer categoryId;

    private Integer supplierId;

    private Integer quantity;

    private String size;

    private BigDecimal unitWeight;

    private List<MultipartFile> pictures;

    private String currentOrder;

    private String idSku;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal unitPrice;

    @NotNull
    private Integer unitsInStock;

    private Integer unitsOnOrder;

    private String productAvailable;

    private String discountAvailable;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;
}

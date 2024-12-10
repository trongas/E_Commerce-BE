package com.example.e_commerce.mapper;

import com.example.e_commerce.dto.request.ProductRequest;
import com.example.e_commerce.dto.response.ProductPictureResponse;
import com.example.e_commerce.dto.response.ProductResponse;
import com.example.e_commerce.entity.Category;
import com.example.e_commerce.entity.Product;
import com.example.e_commerce.entity.ProductPicture;
import com.example.e_commerce.entity.Suppliers;
import com.example.e_commerce.enums.Availability;
import com.example.e_commerce.enums.DiscountAvailability;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public Product convertToEntity(ProductRequest request, List<ProductPicture> productPictures) {
        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setProductDesc(request.getProductDesc());
        product.setUnitPrice(request.getUnitPrice());
        product.setUnitsInStock(request.getUnitsInStock());
        product.setProductAvailable(Availability.valueOf(request.getProductAvailable()));
        product.setDiscountAvailable(DiscountAvailability.valueOf(request.getDiscountAvailable()));
        product.setRating(request.getRating());

        for (ProductPicture picture : productPictures) {
            picture.setProduct(product);
        }
        product.setProductPictures(productPictures);

        return product;
    }


    public ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setProductId(product.getProductId());
        response.setProductName(product.getProductName());
        response.setProductDesc(product.getProductDesc());
        response.setCategoryName(product.getCategory() != null ? product.getCategory().getCategoryName() : null);
        response.setSupplierName(product.getSupplier() != null ? product.getSupplier().getCompanyName() : null);
        response.setQuantity(product.getQuantity());
        response.setSize(product.getSize());
        response.setUnitWeight(product.getUnitWeight());
        response.setCurrentOrder(product.getCurrentOrder());
        response.setIdSku(product.getIdSku());
        response.setUnitPrice(product.getUnitPrice());
        response.setUnitsInStock(product.getUnitsInStock());
        response.setUnitsOnOrder(product.getUnitsOnOrder());
        response.setProductAvailable(product.getProductAvailable().toString());
        response.setDiscountAvailable(product.getDiscountAvailable().toString());
        response.setRating(product.getRating());

        if (product.getProductPictures() != null) {
            response.setPictures(product.getProductPictures()
                    .stream().map(this::convertToProductImageDTO).collect(Collectors.toList()));
        } else {
            response.setPictures(Collections.emptyList());
        }

        return response;
    }

    public void updateEntityFromRequest(Product product, ProductRequest request, List<ProductPicture> productPictures) {
        if (request.getProductName() != null) product.setProductName(request.getProductName());
        if (request.getProductDesc() != null) product.setProductDesc(request.getProductDesc());
        if (request.getUnitPrice() != null) product.setUnitPrice(request.getUnitPrice());
        if (request.getUnitsInStock() != null) product.setUnitsInStock(request.getUnitsInStock());

        if (request.getProductAvailable() != null) {
            product.setProductAvailable(Availability.valueOf(request.getProductAvailable()));
        }
        if (request.getDiscountAvailable() != null) {
            product.setDiscountAvailable(DiscountAvailability.valueOf(request.getDiscountAvailable()));
        }
        if (request.getRating() != null) product.setRating(request.getRating());
    }

    private ProductPictureResponse convertToProductImageDTO(ProductPicture image) {
        ProductPictureResponse dto = new ProductPictureResponse();
        dto.setPictureUrl(image.getPictureUrl());
        return dto;
    }

}

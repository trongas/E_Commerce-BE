package com.example.e_commerce.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.e_commerce.dto.request.ProductRequest;
import com.example.e_commerce.dto.response.ProductResponse;
import com.example.e_commerce.entity.Product;
import com.example.e_commerce.entity.ProductPicture;
import com.example.e_commerce.exception.CustomException;
import com.example.e_commerce.exception.ResourceNotFoundException;
import com.example.e_commerce.mapper.ProductMapper;
import com.example.e_commerce.repository.ProductRepository;
import com.example.e_commerce.services.CloudinaryService;
import com.example.e_commerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@EnableSpringDataWebSupport

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CloudinaryService cloudinaryService;
    private final Cloudinary cloudinary;

    @Override
    public Page<ProductResponse> getAllProducts(String name, Boolean status, int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortField));
        Page<Product> productPage;

        if (name != null && !name.isEmpty()) {
            productPage = productRepository.findByProductNameContainingIgnoreCase(name, pageable);
        } else {
            productPage = productRepository.findAll(pageable);
        }

        return productPage.map(productMapper::toResponse);
    }

    @Override
    public ProductResponse getProductById(Integer id) {
        return productRepository.findById(id)
                .map(productMapper::toResponse).orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
    }

    @Override
    public Page<ProductResponse> searchProductsByName(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByProductNameContainingIgnoreCase(name, pageable)
                .map(productMapper::toResponse);
    }

    @Override
    public Page<ProductResponse> filterProductsByPrice(BigDecimal minPrice, BigDecimal maxPrice, int pageNumber, int pageSize) {
        if (minPrice == null || maxPrice == null || minPrice.compareTo(BigDecimal.ZERO) < 0 || maxPrice.compareTo(minPrice) < 0) {
            throw new CustomException("Invalid price range.");
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByUnitPriceBetween(minPrice, maxPrice, pageable)
                .map(productMapper::toResponse);
    }

    @Override
    public ProductResponse saveProduct(ProductRequest productRequest) {

        List<ProductPicture> productImages = productRequest.getPictures().stream()
                .map(file -> {
                    try {

                        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                        ProductPicture image = new ProductPicture();
                        image.setPictureUrl(uploadResult.get("url").toString());
                        return image;
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to upload image", e);
                    }
                }).collect(Collectors.toList());
        Product product = productMapper.convertToEntity(productRequest, productImages);

        // Save product to the database
        Product savedProduct = productRepository.save(product);

        // Return the response
        return productMapper.toResponse(savedProduct);


    }

    @Override
    public ProductResponse updateProduct(Integer productId, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

        // Upload new images if provided
        List<String> uploadedUrls = new ArrayList<>();
        if (productRequest.getPictures() != null && !productRequest.getPictures().isEmpty()) {
            try {
                uploadedUrls = cloudinaryService.uploadImages(productRequest.getPictures());
            } catch (IOException e) {
                throw new CustomException("Error uploading images: " + e.getMessage());
            }
        }

        // Update product details
//        productMapper.updateEntityFromRequest(existingProduct, productRequest, uploadedUrls);

        // Save updated product
        Product updatedProduct = productRepository.save(existingProduct);

        return productMapper.toResponse(updatedProduct);
    }


    @Override
    public ProductResponse deleteProduct(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new CustomException("Product with ID " + id + " not found.");
        }
        productRepository.deleteById(id);
        return null;
    }
}

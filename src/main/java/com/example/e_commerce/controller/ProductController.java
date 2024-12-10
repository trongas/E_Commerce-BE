package com.example.e_commerce.controller;

import com.example.e_commerce.dto.request.ProductRequest;
import com.example.e_commerce.dto.response.ProductResponse;
import com.example.e_commerce.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")

@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "productId") String sortField,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortDirection
    ) {
        Page<ProductResponse> products = productService.getAllProducts(name, null, pageNumber, pageSize, sortField, sortDirection);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);

    }

    // Tìm kiếm sản phẩm theo tên
    @GetMapping("/search")
    public Page<ProductResponse> searchProductsByName(@RequestParam String name,
                                                      @RequestParam(defaultValue = "0") int pageNumber,
                                                      @RequestParam(defaultValue = "10") int pageSize) {
        return productService.searchProductsByName(name, pageNumber, pageSize);
    }

    // Lọc sản phẩm theo giá
    @GetMapping("/filter")
    public Page<ProductResponse> filterProductsByPrice(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam int pageSize) {
        return productService.filterProductsByPrice(minPrice, maxPrice, pageNumber, pageSize);
    }

//    @PostMapping(consumes = "multipart/form-data")
//    public ResponseEntity<ProductResponse> createProduct(
//            @RequestPart("product") ProductRequest productRequest,
//            @RequestPart("pictures") List<MultipartFile> pictures) {
//        productRequest.setPictures(pictures); // Assign images to DTO
//        ProductResponse response = productService.saveProduct(productRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse addProduct(@Valid @ModelAttribute ProductRequest productRequest) {
        return productService.saveProduct(productRequest);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Integer productId,
            @ModelAttribute ProductRequest productRequest) {
        ProductResponse updatedProduct = productService.updateProduct(productId, productRequest);
        return ResponseEntity.ok(updatedProduct);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCombo(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Membership with ID " + id + " has been successfully deleted.");
    }
}

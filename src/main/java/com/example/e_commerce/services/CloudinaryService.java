package com.example.e_commerce.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    // Khai báo các thông tin cấu hình Cloudinary từ application.properties
    @Value("${cloudinary.cloud_name}")
    private String cloudName;

    @Value("${cloudinary.api_key}")
    private String apiKey;

    @Value("${cloudinary.api_secret}")
    private String apiSecret;

    public CloudinaryService() {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }

    public List<String> uploadImages(List<MultipartFile> pictures) throws IOException {
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile picture : pictures) {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(
                    picture.getBytes(),
                    ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("url");
            imageUrls.add(imageUrl);
        }
        return imageUrls;
    }
}

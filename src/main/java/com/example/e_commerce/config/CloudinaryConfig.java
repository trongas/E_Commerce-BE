package com.example.e_commerce.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean(name = "customCloudinaryConfig")
    public Cloudinary cloudinaryConfig() {
        Cloudinary cloudinary = null;
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dqcetpx5s");
        config.put("api_key", "974769531468468");
        config.put("api_secret", "yNBSCI9Zt49pii6dLwqI2FATqtg");
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }


}


package com.example.e_commerce.dto.request;

import com.example.e_commerce.enums.MembershipType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class MembershipRequest {
    @NotNull(message = "Membership type is required")
    private MembershipType membershipType;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String membershipDesc;

}

package com.example.e_commerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class MembershipResponse {
    private Integer membershipId;
    private String membershipType;
    private String membershipDesc;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

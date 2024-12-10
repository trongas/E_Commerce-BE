package com.example.e_commerce.services;

import com.example.e_commerce.dto.request.MembershipRequest;
import com.example.e_commerce.dto.response.MembershipResponse;
import com.example.e_commerce.entity.Membership;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MembershipService {
    Page<MembershipResponse> getAllMemberships(Pageable pageable);
    MembershipResponse getMembershipById(Integer id);
    Membership createMembership(MembershipRequest requestDTO);
    MembershipResponse updateMembership(Integer membershipId, MembershipRequest membershipRequest);

    void deleteMembership(Integer id);
}

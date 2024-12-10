package com.example.e_commerce.mapper;

import com.example.e_commerce.dto.request.MembershipRequest;
import com.example.e_commerce.dto.response.MembershipResponse;
import com.example.e_commerce.entity.Membership;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {

    // Chuyển từ Membership Entity sang MembershipResponseDTO
    public MembershipResponse convertToMemberResponse(Membership membership) {
        MembershipResponse dto = new MembershipResponse();
        dto.setMembershipId(membership.getMembershipId());
        dto.setMembershipType(membership.getMembershipType().name());
        dto.setMembershipDesc(membership.getMembershipDesc());
        return dto;
    }
    public void updateEntityFromRequest(Membership membership, MembershipRequest request) {
        if (request.getMembershipType() != null) {
            membership.setMembershipType(request.getMembershipType());
        }
        if (request.getMembershipDesc() != null) {
            membership.setMembershipDesc(request.getMembershipDesc());
        }
    }

    public Membership convertToEntity(MembershipRequest membershipRequest) {
        Membership membership = new Membership();
        membership.setMembershipDesc(membershipRequest.getMembershipDesc());
        membership.setMembershipType(membershipRequest.getMembershipType());

        return membership;
    }
}

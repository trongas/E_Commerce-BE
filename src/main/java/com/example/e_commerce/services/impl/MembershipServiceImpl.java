package com.example.e_commerce.services.impl;

import com.example.e_commerce.dto.request.MembershipRequest;
import com.example.e_commerce.dto.response.MembershipResponse;
import com.example.e_commerce.entity.Membership;
import com.example.e_commerce.exception.CustomException;
import com.example.e_commerce.exception.ResourceNotFoundException;
import com.example.e_commerce.mapper.MembershipMapper;
import com.example.e_commerce.repository.MembershipRepository;
import com.example.e_commerce.services.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;
    private final MembershipMapper membershipMapper;

    @Override
    public Page<MembershipResponse> getAllMemberships(Pageable pageable) {
        Page<Membership> memberships = membershipRepository.findAll(pageable);
        if (memberships.isEmpty()) {
            throw new CustomException("No memberships found");
        }
        return memberships.map(membershipMapper::convertToMemberResponse);

    }

    @Override
    public MembershipResponse getMembershipById(Integer id) {
        return membershipRepository.findById(id).map(membershipMapper::convertToMemberResponse)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Membership not found with id " + id));

    }

    @Override
    public Membership createMembership(MembershipRequest membershipRequest) {
        if (membershipRepository.existsByMembershipType(membershipRequest.getMembershipType())) {
            throw new CustomException("Membership type already exists: " + membershipRequest.getMembershipType());
        }
        Membership membership = membershipMapper.convertToEntity(membershipRequest);
        return membershipRepository.save(membership);
    }

    @Override
    public void deleteMembership(Integer id) {
        if (!membershipRepository.existsById(id)) {
            throw new CustomException("Cannot delete, Membership not found with ID: " + id);
        }
        membershipRepository.deleteById(id);
    }
}

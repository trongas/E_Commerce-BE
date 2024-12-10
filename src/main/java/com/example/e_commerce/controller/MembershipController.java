package com.example.e_commerce.controller;

import com.example.e_commerce.dto.request.MembershipRequest;
import com.example.e_commerce.dto.response.MembershipResponse;
import com.example.e_commerce.entity.Membership;
import com.example.e_commerce.services.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/memberships")
@RequiredArgsConstructor
public class MembershipController {
    private final MembershipService membershipService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public Page<MembershipResponse> getAllMemberships(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return membershipService.getAllMemberships(pageable);
    }

    @GetMapping("/{id}")
    public MembershipResponse getMembershipById(@PathVariable Integer id) {
        return membershipService.getMembershipById(id);
    }

    @PostMapping
    public ResponseEntity<Membership> createMembership(@RequestBody MembershipRequest Request) {
        Membership membership = membershipService.createMembership(Request);
        return ResponseEntity.ok(membership);
    }

    @PutMapping("/{membershipId}")
    public ResponseEntity<MembershipResponse> updateMembership(
            @PathVariable Integer membershipId,
            @RequestBody MembershipRequest membershipRequest) {
        MembershipResponse updatedMembership = membershipService.updateMembership(membershipId, membershipRequest);
        return ResponseEntity.ok(updatedMembership);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCombo(@PathVariable Integer id) {
        membershipService.deleteMembership(id);
        return ResponseEntity.ok("Membership with ID " + id + " has been successfully deleted.");
    }
}

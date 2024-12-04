package com.example.e_commerce.repository;

import com.example.e_commerce.entity.Membership;
import com.example.e_commerce.enums.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Integer> {
    // Custom query để kiểm tra xem MembershipType đã tồn tại chưa
    boolean existsByMembershipType(MembershipType membershipType);

}


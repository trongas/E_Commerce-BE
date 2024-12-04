package com.example.e_commerce.entity;

import com.example.e_commerce.enums.MembershipType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Membership")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer membershipId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MembershipType membershipType;

    @Lob
    private String membershipDesc;


}


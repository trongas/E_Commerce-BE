package com.example.e_commerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Shipper")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shipperId;

    private String shipperName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private String shipperAddress;

    private String website;
}

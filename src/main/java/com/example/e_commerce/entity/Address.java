package com.example.e_commerce.entity;

import com.example.e_commerce.enums.AddressType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String addressLine1;

    private String houseOrAptNo;

    private String city;

    private String provinceState;

    private String country;

    private String postalCode;
}



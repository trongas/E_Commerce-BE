package com.example.e_commerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "GiftCard")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer giftCardId;

    @Column(unique = true)
    private String giftCardCode;

    private LocalDate giftCardExpiry;

    private Double giftCardBalance;

    @Enumerated(EnumType.STRING)
    private GiftCardStatus giftCardStatus;
}

enum GiftCardStatus {
    Active, Redeemed, Expired;
}


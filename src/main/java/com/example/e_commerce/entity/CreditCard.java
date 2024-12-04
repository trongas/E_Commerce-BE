package com.example.e_commerce.entity;

import com.example.e_commerce.enums.CardType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "CreditCard")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer creditCardId;

    @ManyToOne
    @JoinColumn(name = "paymentId")
    private Payments payment;

    @Column(length = 16)
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType cardType;

    private LocalDate cardExpiryDate;

    private String cardHolderName;
}



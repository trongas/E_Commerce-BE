package com.example.e_commerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "AccountNumber_Payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountNumberPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountNumberId;

    @Column(nullable = false, length = 15)
    private String accountNumber;

    @Column(unique = true, nullable = false)
    private String transactionNumber;

    private String bankName;

    @ManyToOne
    @JoinColumn(name = "paymentId")
    private Payments payment;
}

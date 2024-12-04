package com.example.e_commerce.entity;

import com.example.e_commerce.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}

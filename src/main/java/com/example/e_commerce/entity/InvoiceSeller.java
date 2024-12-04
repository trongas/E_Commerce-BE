package com.example.e_commerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Invoice_Seller")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceSeller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invoiceSellerId;

    @ManyToOne
    @JoinColumn(name = "invoiceId")
    private Orders invoice;
}

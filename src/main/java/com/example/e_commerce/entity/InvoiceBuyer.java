package com.example.e_commerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Invoice_Buyer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceBuyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invoiceBuyerId;

    @ManyToOne
    @JoinColumn(name = "invoiceId")
    private Orders invoice;
}

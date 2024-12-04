package com.example.e_commerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "paymentId")
    private Payments payment;

    @ManyToOne
    @JoinColumn(name = "discountId")
    private Discount discount;

    private Integer orderNumber;

    private LocalDate orderDate;

    private LocalDate shipDate;

    private LocalDate requiredDate;

    private Double freight;

    private Double salesTax;

    private Double amountPaid;

    private Integer itemQuantity;

    private Double totalAmount;

    private Boolean transactStatus;

    private LocalDateTime paidDate;
}

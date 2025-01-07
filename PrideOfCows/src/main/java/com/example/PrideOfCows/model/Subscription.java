package com.example.PrideOfCows.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data // Lombok annotation to generate getters, setters, toString, etc.
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;

    private Long customerId;
    private Long productId;
    private String productName;
    private String frequency;
    private int quantity;
    private double pricePerUnit;
    private LocalDate startDate;
    private LocalDate endDate;
}

package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Table(name = "rides")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String fromAddress;

    private String toAddress;

    private Double distance;

    private Double cost;

    private String bookingType;

    @Enumerated(EnumType.STRING)
    private RideStatus status;

    private LocalDateTime rideDate;

    private Double penalty;

    private Double refund;

    private String pickupLocation;
    private String dropLocation;

    //private String status; // REQUESTED, ACCEPTED, STARTED, COMPLETED, CANCELLED

    private Long riderId;
}
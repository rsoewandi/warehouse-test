package com.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantityChange;
    private String type;
    private String reference;
    private LocalDateTime createdAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id")
    @JsonBackReference
    private Variant variant;
}

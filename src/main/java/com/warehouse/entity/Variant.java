package com.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String color;
    private String size;
    private Double price;
    private Integer stock = 0;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonBackReference
    private Item item;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Price> prices;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Stock> stocks;
}

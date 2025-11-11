package com.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.warehouse.request.RequestItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    private String name;
    private String description;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Variant> variants;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;

    public static Item reqItem(RequestItem req){
        return Item.builder()
                .name(req.getName())
                .sku(req.getSku())
                .description(req.getDescription())
                .createdBy(req.getUserName())
                .createdDatetime(LocalDateTime.now())
                .build();
    }

}

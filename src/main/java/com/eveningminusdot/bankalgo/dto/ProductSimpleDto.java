package com.eveningminusdot.bankalgo.dto;

import com.eveningminusdot.bankalgo.domain.Product;

public class ProductSimpleDto {
    private Long id;
    private String name;
    private String type;

    public ProductSimpleDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.type = product.getType().toString();
    }
}

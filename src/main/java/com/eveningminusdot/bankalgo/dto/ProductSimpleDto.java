package com.eveningminusdot.bankalgo.dto;

import com.eveningminusdot.bankalgo.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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

package com.eveningminusdot.bankalgo.dto;

import com.eveningminusdot.bankalgo.domain.Benefit;
import com.eveningminusdot.bankalgo.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
public class ProductDetailDto {
    private Long id;
    private String name;
    private String type;
    private String description;

    public ProductDetailDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.type = product.getType().toString();
        this.description = product.getDescription();
    }

}

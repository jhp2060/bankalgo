package com.eveningminusdot.bankalgo.dto;

import com.eveningminusdot.bankalgo.domain.Benefit;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BenefitSimpleDto {
    private Long id;
    private String productName;
    private String name;
    private String type;
    private String description;

    public BenefitSimpleDto(Benefit benefit) {
        this.id = benefit.getId();
        this.productName = benefit.getProduct().getName();
        this.name = benefit.getName();
        this.type = benefit.getType().toString();
        this.description = benefit.getDescription();
    }
}

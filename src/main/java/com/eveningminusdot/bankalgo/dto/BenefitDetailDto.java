package com.eveningminusdot.bankalgo.dto;

import com.eveningminusdot.bankalgo.domain.Benefit;
import com.eveningminusdot.bankalgo.domain.Requirement;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
public class BenefitDetailDto {
    private Long id;
    private String name;
    private String type;
    private String description;
    private String productName;
    private Set<RequirementSimpleDto> requirements;

    public BenefitDetailDto(Benefit b, Set<RequirementSimpleDto> r) {
        this.id = b.getId();
        this.name = b.getName();
        this.type = b.getType().toString();
        this.description = b.getDescription();
        this.productName = b.getProduct().getName();
        this.requirements = r;
    }

}

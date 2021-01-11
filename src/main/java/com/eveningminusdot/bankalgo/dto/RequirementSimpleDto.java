package com.eveningminusdot.bankalgo.dto;

import com.eveningminusdot.bankalgo.domain.Requirement;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequirementSimpleDto {
    private Long id;
    private String description;
    private String inquiryContent;
    private String type;

    public RequirementSimpleDto (Requirement r) {
        this.id = r.getId();
        this.description = r.getDescription();
        this.inquiryContent = r.getInquiryContent();
        this.type = r.getType().toString();
    }
}

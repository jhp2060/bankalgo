package com.eveningminusdot.bankalgo.domain;

import com.sun.istack.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Requirement {

    public enum RequirementType {
        AGE,                    // 연력
        HAVING_HOUSE,           // 주택 보유
        HOUSE_HOLDER,           // 세대주
        CREDIT_RATING,          // 신용등급
        ETC,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private String inquiryContent;

    @Column
    @Enumerated(EnumType.STRING)
    private RequirementType type;

    @ManyToMany(mappedBy = "requirements")
    Set<Benefit> benefits;

    @Builder
    public Requirement(String description, String inquiryContent,
                       RequirementType type, Set<Benefit> benefits) {
        this.description = description;
        this.inquiryContent = inquiryContent;
        this.type = type;
        this.benefits = benefits;
    }
}

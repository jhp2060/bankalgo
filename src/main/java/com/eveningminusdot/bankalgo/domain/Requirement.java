package com.eveningminusdot.bankalgo.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Requirement {
    enum RequirementType {
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
}

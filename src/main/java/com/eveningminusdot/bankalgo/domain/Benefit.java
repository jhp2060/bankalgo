package com.eveningminusdot.bankalgo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Benefit {

    enum BenefitType {
        ENROLLMENT,         // 가입
        PRIME_RATE,         // 금리우대
        DEDUCTION,          // 소득공제
        TAX_FREE,           // 비과세
        CHARGE_FREE,        // 수수료 면제
        ETC,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private BenefitType type;

    @Column
    private String description;

    @ManyToOne
    private Product product;

    @ManyToMany
    private Set<Condition> conditions;
}

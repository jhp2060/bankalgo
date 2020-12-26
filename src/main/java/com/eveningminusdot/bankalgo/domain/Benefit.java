package com.eveningminusdot.bankalgo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Benefit {

    public enum BenefitType {
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
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToMany
    @JoinTable(
            name = "benefit_requirement",
            joinColumns = @JoinColumn(name = "benefit_id"),
            inverseJoinColumns = @JoinColumn(name = "requirement_id")
    )
    private Set<Requirement> requirements;

    @Builder
    public Benefit(String name, String type,
                   String description, Product product) {
        this.name = name;
        this.type = BenefitType.valueOf(type);
        this.description = description;
        this.product = product;
    }
}

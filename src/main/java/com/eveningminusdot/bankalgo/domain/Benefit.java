package com.eveningminusdot.bankalgo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Benefit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private BenefitType type;

    @ManyToOne
    private Product product;

}

enum BenefitType {
    ENROLLMENT,         // 가입
    PRIME_RATE,         // 금리우대
    DEDUCTION,          // 소득공제
    TAX_EXEMPTION,      // 비과세
}
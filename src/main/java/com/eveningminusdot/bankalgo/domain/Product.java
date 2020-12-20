package com.eveningminusdot.bankalgo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Product {

    public enum ProductType {
        GENERAL_DEPOSIT,        // 요구불예금
        INSTALLMENT_SAVING,     // 적금
        TIME_DEPOSIT,           // 예금
        FUND,                   // 펀드
        BANCASSURANCE,          // 방카슈랑스
        CREDIT_CARD,            // 신용카드
        PERSONAL_LOAN,          // 개인대출
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private ProductType type;

    @OneToMany
    private Set<Benefit> benefits;
}

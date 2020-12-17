package com.eveningminusdot.bankalgo.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Condition {
    enum ConditionType {
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
    @Enumerated(EnumType.STRING)
    private ConditionType type;

    @ManyToMany
    Set<User> users;
}

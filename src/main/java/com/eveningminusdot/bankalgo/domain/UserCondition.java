package com.eveningminusdot.bankalgo.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class UserCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Condition condition;

    @Column
    private boolean isSatisfied;

    @Column
    private LocalDate normDate;
}

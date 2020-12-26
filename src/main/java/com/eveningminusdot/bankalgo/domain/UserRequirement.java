package com.eveningminusdot.bankalgo.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class UserRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="requirement_id")
    private Requirement requirement;

    @Column
    private boolean isSatisfied;

    @Column
    private LocalDate normDate;
}

package com.eveningminusdot.bankalgo.domain;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
public class User {

    enum JobType {
        SALARIED_EMPLOYEE,          // 급여소득자
        INDIVIDUAL_BUSINESS,        // 개인사업자
        CORPORATION_CEO,            // 법인대표
        STUDENT,                    // 학생
        HOMEMAKER,                  // 주부
        ETC
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Date birth;

    @Column
    private JobType job;

    @Column
    private int annulIncome;

    @OneToMany(mappedBy = "user")
    Set<UserRequirement> requirements;
}

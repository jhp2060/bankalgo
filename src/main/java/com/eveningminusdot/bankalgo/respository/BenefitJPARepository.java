package com.eveningminusdot.bankalgo.respository;

import com.eveningminusdot.bankalgo.domain.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenefitJPARepository extends JpaRepository<Benefit, Long> {
}
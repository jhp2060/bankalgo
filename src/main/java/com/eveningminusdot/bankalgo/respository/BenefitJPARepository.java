package com.eveningminusdot.bankalgo.respository;

import com.eveningminusdot.bankalgo.domain.Benefit;
import com.eveningminusdot.bankalgo.domain.Product;
import com.eveningminusdot.bankalgo.domain.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenefitJPARepository extends JpaRepository<Benefit, Long> {
    List<Benefit> findByProductId(Long id);
    List<Benefit> findByRequirementsContains(Requirement r);
}


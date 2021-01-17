package com.eveningminusdot.bankalgo.respository;

import com.eveningminusdot.bankalgo.domain.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RequirementJPARepository extends JpaRepository<Requirement, Long> {


}

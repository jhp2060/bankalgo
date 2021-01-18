package com.eveningminusdot.bankalgo.service;

import com.eveningminusdot.bankalgo.domain.Benefit;
import com.eveningminusdot.bankalgo.domain.Requirement;
import com.eveningminusdot.bankalgo.dto.BenefitDetailDto;
import com.eveningminusdot.bankalgo.dto.BenefitSimpleDto;
import com.eveningminusdot.bankalgo.dto.RequirementSimpleDto;
import com.eveningminusdot.bankalgo.respository.BenefitJPARepository;
import com.eveningminusdot.bankalgo.respository.RequirementJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class BenefitService {
    private final BenefitJPARepository benefitRepo;
    private final RequirementJPARepository requirementRepo;


    public BenefitDetailDto findById(Long id) {
        Benefit benefit = benefitRepo.findById(id).orElseThrow();
        Set<RequirementSimpleDto> requirements = new HashSet<>();
        for (Requirement r : benefit.getRequirements())
            requirements.add(new RequirementSimpleDto(r));
        return new BenefitDetailDto(benefit, requirements);
    }

    public List<BenefitSimpleDto> findAll() {
        List<Benefit> benefits =  benefitRepo.findAll();
        List<BenefitSimpleDto> benefitSimpleDtos = new LinkedList<>();
        for (Benefit b: benefits) benefitSimpleDtos.add(new BenefitSimpleDto(b));
        return benefitSimpleDtos;
    }

    public List<BenefitSimpleDto> findByProductId(Long productId) {
        List<BenefitSimpleDto> benefitDtos = new LinkedList<>();
        List<Benefit> benefits = benefitRepo.findByProductId(productId);
        for (Benefit b: benefits) benefitDtos.add(new BenefitSimpleDto(b));
        return benefitDtos;
    }

    public List<BenefitSimpleDto> findByRequirementId(Long requirementId) {
        List<BenefitSimpleDto> benefitDtos = new LinkedList<>();
        List<Benefit> benefits = benefitRepo.findByRequirementsContains(
                requirementRepo.findById(requirementId).orElseThrow());
        for (Benefit b: benefits) benefitDtos.add(new BenefitSimpleDto(b));
        return benefitDtos;
    }

    public List<BenefitSimpleDto> findAllFiltered(Long productId, Long requirementId) {
        List<BenefitSimpleDto> benefitDtos = new LinkedList<>();
        List<Benefit> benefits = benefitRepo.findByProductIdAndRequirementsContains(
                productId, requirementRepo.findById(requirementId).orElseThrow()
        );
        for (Benefit b: benefits) benefitDtos.add(new BenefitSimpleDto(b));
        return benefitDtos;
    }

}

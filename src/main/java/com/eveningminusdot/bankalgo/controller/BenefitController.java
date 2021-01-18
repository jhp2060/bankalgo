package com.eveningminusdot.bankalgo.controller;

import com.eveningminusdot.bankalgo.dto.BenefitDetailDto;
import com.eveningminusdot.bankalgo.dto.BenefitSimpleDto;
import com.eveningminusdot.bankalgo.service.BenefitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BenefitController {
    private final BenefitService benefitService;

    @GetMapping("/api/v1/benefits")
    public List<BenefitSimpleDto> findAllFiltered(
            @RequestParam(required = false) Long productId,
            @RequestParam(required = false) Long requirementId) {
        if (productId == null && requirementId == null)
            return benefitService.findAll();
        else if (productId == null)
            return benefitService.findByRequirementId(requirementId);
        else if (requirementId == null)
            return benefitService.findByProductId(productId);
        return benefitService.findAllFiltered(productId, requirementId);
    }

    @GetMapping("/api/v1/benefits/{id}")
    public BenefitDetailDto findById(@PathVariable Long id) {
        return benefitService.findById(id);
    }

}

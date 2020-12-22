package com.eveningminusdot.bankalgo.service;

import com.eveningminusdot.bankalgo.domain.Benefit;
import com.eveningminusdot.bankalgo.domain.Product;
import com.eveningminusdot.bankalgo.dto.BenefitSimpleDto;
import com.eveningminusdot.bankalgo.dto.ProductDetailDto;
import com.eveningminusdot.bankalgo.dto.ProductSimpleDto;
import com.eveningminusdot.bankalgo.respository.BenefitJPARepository;
import com.eveningminusdot.bankalgo.respository.ProductJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductJPARepository productRepository;
    private final BenefitJPARepository benefitRepository;

    public List<ProductSimpleDto> findAll() {
        List<Product> entities = productRepository.findAll();
        List<ProductSimpleDto> ret = new ArrayList<>();
        for (Product p : entities)
            ret.add(new ProductSimpleDto(p));
        return ret;
    }

    public ProductDetailDto findById(Long id) {
        List<Benefit> benefits = benefitRepository.findByProductId(id);
        Set<BenefitSimpleDto> benefitSimpleDtos = new HashSet<>();
        for (Benefit b : benefits)
            benefitSimpleDtos.add(new BenefitSimpleDto(b));
        return new ProductDetailDto(
                productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(
                        "No Product with id " + id + ".\n"
                )), benefitSimpleDtos
        );
    }
}

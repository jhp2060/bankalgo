package com.eveningminusdot.bankalgo.controller;

import com.eveningminusdot.bankalgo.dto.ProductDetailDto;
import com.eveningminusdot.bankalgo.service.ProductService;
import com.eveningminusdot.bankalgo.dto.ProductSimpleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/hello")
    public String hello(String name) {
        return "Hello : " + name;
    }

    @GetMapping("/api/v1/products")
    public List<ProductSimpleDto> findAll() {
        return productService.findAll();
    }

    @GetMapping("/api/v1/products/{id}")
    public ProductDetailDto findById(@PathVariable Long id) {
        return productService.findById(id);
    }

}

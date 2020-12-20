package com.eveningminusdot.bankalgo.controller;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    ProductController () {
        System.out.println("\n\nHELLO THIS IS PRODUCT CONTROLLER\n\n");
    }

    @GetMapping("/hello")
    public String hello(String name) {
        return "Hello : " + name;
    }

}

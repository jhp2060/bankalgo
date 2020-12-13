package com.eveningminusdot.bankalgo.controller;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor
@RestController
public class ProductController {

    @GetMapping("/hello")
    public String hello(String name) {
        return "Hello : " + name;
    }

}

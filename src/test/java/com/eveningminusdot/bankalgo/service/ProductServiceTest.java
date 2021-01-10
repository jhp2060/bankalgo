package com.eveningminusdot.bankalgo.service;

import com.eveningminusdot.bankalgo.domain.Product;
import com.eveningminusdot.bankalgo.dto.ProductSimpleDto;
import com.eveningminusdot.bankalgo.respository.ProductJPARepository;
import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Profile("test")
public class ProductServiceTest {
    @Autowired
    private ProductJPARepository productRepo;

    @Autowired
    private ProductService productService;

    @After
    public void tearDown(){
        productRepo.deleteAll();
    }

    @Test
    public void findAllWithTypeSuccess() {
        Faker faker = new Faker(new Locale("ko-KO"));
        for (int i = 0; i < 2; ++i)
            productRepo.save(Product.builder()
                .name(faker.lorem().sentence(3))
                .description(faker.lorem().sentence(30))
                .type(Product.ProductType.CREDIT_CARD.toString())
                .build()
        );
        for (int i = 0; i < 5; ++i)
            productRepo.save(Product.builder()
                .name(faker.lorem().sentence(3))
                .description(faker.lorem().sentence(30))
                .type(Product.ProductType.FUND.toString())
                .build()
            );

        assertThat(productService.findAll("").size()).isEqualTo(7);
        assertThat(productService.findAll("CREDIT_CARD").size()).isEqualTo(2);
        assertThat(productService.findAll("FUND").size()).isEqualTo(5);

    }
}

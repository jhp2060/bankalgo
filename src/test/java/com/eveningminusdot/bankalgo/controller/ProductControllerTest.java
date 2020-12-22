package com.eveningminusdot.bankalgo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.eveningminusdot.bankalgo.domain.Benefit;
import com.eveningminusdot.bankalgo.domain.Product;
import com.eveningminusdot.bankalgo.dto.ProductDetailDto;
import com.eveningminusdot.bankalgo.respository.BenefitJPARepository;
import com.eveningminusdot.bankalgo.respository.ProductJPARepository;
import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductJPARepository productRepo;

    @Autowired
    private BenefitJPARepository benefitRepo;

    @After
    public void tearDown(){
        // cascade 고
        benefitRepo.deleteAll();
        productRepo.deleteAll();
    }

    @Test
    public void findAllSuccess() {

        Faker faker = new Faker(new Locale("ko-KO"));
        for (int i = 0; i < 10; ++i) {
            String productName = faker.lorem().sentence(2);
            String description = faker.lorem().sentence(30);

            productRepo.save(Product.builder()
                    .name(productName)
                    .description(description)
                    .type(Product.ProductType.CREDIT_CARD.toString())
                    .build()
            );
        }

        String responseUrl = "http://localhost:" + port + "/api/v1/products";

        ResponseEntity<List> responseEntity
                = restTemplate.getForEntity(responseUrl, List.class);

        @SuppressWarnings("unchecked")
        LinkedHashMap<String, String> now =
                (LinkedHashMap<String, String>) (responseEntity.getBody()).get(0);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(10);
    }

    @Test
    public void findByIdSuccess(){

        Faker faker = new Faker(new Locale("ko-KO"));
        String productName = faker.lorem().sentence(2);
        String description = faker.lorem().sentence(30);

        Product p1 = productRepo.save(Product.builder()
                .name(productName)
                .description(description)
                .type(Product.ProductType.CREDIT_CARD.toString())
                .build()
        );
        Product p2 = productRepo.save(Product.builder()
                .name(productName)
                .description(description)
                .type(Product.ProductType.CREDIT_CARD.toString())
                .build()
        );

        Benefit b = benefitRepo.save(Benefit.builder()
                .name(faker.lorem().sentence(3))
                .product(p1)
                .type(Benefit.BenefitType.ENROLLMENT.toString())
                .build()
        );
        for (int i = 0; i < 3; ++i) {
            benefitRepo.save(Benefit.builder()
                    .name(faker.lorem().sentence(3))
                    .product(p2)
                    .type(Benefit.BenefitType.ENROLLMENT.toString())
                    .build()
            );
        }

        String responseUrl = "http://localhost:"+port+"/api/v1/products/"+p2.getId();

        ResponseEntity<ProductDetailDto> responseEntity
                = restTemplate.getForEntity(responseUrl, ProductDetailDto.class);
        ProductDetailDto now = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(now).isNotNull();
        assertThat(now.getBenefits().size()).isEqualTo(3);

        responseUrl = "http://localhost:"+port+"/api/v1/products/"+p1.getId();
        responseEntity = restTemplate.getForEntity(responseUrl, ProductDetailDto.class);
        now = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(now).isNotNull();
        assertThat(now.getBenefits().size()).isEqualTo(1);
    }
}

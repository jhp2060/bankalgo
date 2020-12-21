package com.eveningminusdot.bankalgo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.eveningminusdot.bankalgo.domain.Product;
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

    @After
    public void tearDown() throws Exception {
        productRepo.deleteAll();
    }

    @Test
    public void findAllSuccess() throws Exception {

        for (int i = 0; i < 10; ++i) {
            Faker faker = new Faker(new Locale("ko-KO"));
            String productName = faker.lorem().sentence(2);
            String description = faker.lorem().sentence(30);

            productRepo.save(Product.builder()
                    .name(productName)
                    .description(description)
                    .type(Product.ProductType.CREDIT_CARD.toString())
                    .build()
            );
        }

        String responseUrl = "http://localhost:"+port+"/api/v1/products";

        ResponseEntity<List> responseEntity
                = restTemplate.getForEntity(responseUrl, List.class);

        LinkedHashMap<String, String> now =
                (LinkedHashMap<String, String>) responseEntity.getBody().get(0);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size() == 10);
    }

}

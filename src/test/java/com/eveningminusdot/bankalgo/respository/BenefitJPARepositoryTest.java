package com.eveningminusdot.bankalgo.respository;


import com.eveningminusdot.bankalgo.domain.Benefit;
import com.eveningminusdot.bankalgo.domain.Product;
import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BenefitJPARepositoryTest {

    @Autowired
    private BenefitJPARepository benefitRepo;

    @Autowired
    private ProductJPARepository productRepo;

    @Test
    public void findByProductIdSuccess() {
        Faker faker = new Faker(new Locale("ko-KO"));
        Product p = productRepo.save(Product.builder()
                .name(faker.lorem().sentence(3))
                .description(faker.lorem().sentence(30))
                .type(Product.ProductType.CREDIT_CARD.toString())
                .build()
        );
        Benefit b1 = benefitRepo.save(Benefit.builder()
                .name(faker.lorem().sentence(3))
                .description(faker.lorem().sentence(30))
                .type(Benefit.BenefitType.ENROLLMENT.toString())
                .product(p)
                .build()
        );

        Benefit b2 = benefitRepo.save(Benefit.builder()
                .name(faker.lorem().sentence(3))
                .description(faker.lorem().sentence(30))
                .type(Benefit.BenefitType.ENROLLMENT.toString())
                .product(productRepo.save(Product.builder()
                        .name(faker.lorem().sentence(3))
                        .description(faker.lorem().sentence(30))
                        .type(Product.ProductType.CREDIT_CARD.toString())
                        .build()))
                .build()
        );

        assertThat(benefitRepo.findByProductId(1L).size()).isEqualTo(1);
        assertThat(benefitRepo.findByProductId(1L).get(0)).isEqualTo(b1);
    }
}

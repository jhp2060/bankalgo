package com.eveningminusdot.bankalgo.respository;


import com.eveningminusdot.bankalgo.domain.Benefit;
import com.eveningminusdot.bankalgo.domain.Product;
import com.eveningminusdot.bankalgo.domain.Requirement;
import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Profile("test")
public class BenefitJPARepositoryTest {

    @Autowired
    private BenefitJPARepository benefitRepo;

    @Autowired
    private ProductJPARepository productRepo;

    @Autowired
    private RequirementJPARepository requirementRepo;

    static Faker faker = new Faker(new Locale("ko-KO"));

    @After
    public void tearDown() {
        // benefit_requirement 먼저 삭제 후 benefit 테이블 삭제
        // benefit-requirement 사이의 소유 관계가 benefit에 있으므로 benefit을 먼저 삭제
        benefitRepo.deleteAll();
        requirementRepo.deleteAll();
        productRepo.deleteAll();
    }

    @Test
    public void findByProductIdSuccess() {
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

    @Test
    public void findByRequirementsContainsSuccess() {
        Requirement[] requirementArray = new Requirement[5];
        Set<Requirement> requirements = new HashSet<>();
        for (int i = 0; i < 5; ++i) {
            Requirement r = requirementRepo.save(Requirement.builder()
                    .description(faker.lorem().sentence(30))
                    .inquiryContent(faker.lorem().sentence(10))
                    .type(Requirement.RequirementType.AGE)
                    .benefits(null)
                    .build()
            );
            requirements.add(r);
            requirementArray[i] = r;
        }

        benefitRepo.save(Benefit.builder()
                .name(faker.lorem().sentence(3))
                .description(faker.lorem().sentence(30))
                .type(Benefit.BenefitType.ENROLLMENT.toString())
                .requirements(null)
                .build()
        );

        Benefit b1 = benefitRepo.save(Benefit.builder()
                .name(faker.lorem().sentence(3))
                .description(faker.lorem().sentence(30))
                .type(Benefit.BenefitType.ENROLLMENT.toString())
                .requirements(requirements)
                .build()
        );

        Set<Benefit> benefits = new HashSet<>();
        benefits.add(b1);

        for (int i = 0; i < 5; ++i) {
            Requirement r = requirementArray[i];
            r.setBenefits(benefits);
            requirementRepo.save(r);
        }

        assertThat(benefitRepo.findByRequirementsContains(requirementArray[0]).get(0).getId())
                .isEqualTo(b1.getId());
        assertThat(benefitRepo.findByRequirementsContains(requirementArray[1]).get(0).getId())
                .isEqualTo(b1.getId());
        assertThat(benefitRepo.findByRequirementsContains(requirementArray[2]).get(0).getId())
                .isEqualTo(b1.getId());
        assertThat(benefitRepo.findByRequirementsContains(requirementArray[3]).get(0).getId())
                .isEqualTo(b1.getId());
        assertThat(benefitRepo.findByRequirementsContains(requirementArray[4]).get(0).getId())
                .isEqualTo(b1.getId());

    }
}

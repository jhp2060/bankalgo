package com.eveningminusdot.bankalgo.respository;

import com.eveningminusdot.bankalgo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductJPARepository extends JpaRepository<Product, Long> {
    List<Product> findAllByType(Product.ProductType type);
}

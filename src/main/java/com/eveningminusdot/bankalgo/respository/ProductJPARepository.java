package com.eveningminusdot.bankalgo.respository;

import com.eveningminusdot.bankalgo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJPARepository extends JpaRepository<Product, Long> {

}

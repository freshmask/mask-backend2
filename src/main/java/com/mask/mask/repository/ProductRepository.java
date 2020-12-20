package com.mask.mask.repository;

import com.mask.mask.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Page<Product> findAll(Specification<Product> productSpecification, Pageable pageable);

    List<Product> findAllByIsActive (String status);
    public List<Product> findProductByIsActive(String isActive);
}

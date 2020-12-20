package com.mask.mask.repository;

import com.mask.mask.entity.PackagePA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackagePARepository extends JpaRepository<PackagePA, String> {
    public List<PackagePA> findPackagePAByProductProductId(String productId);
    Page<PackagePA> findAll(Specification<PackagePA> packagePASpecification, Pageable pageable);
}

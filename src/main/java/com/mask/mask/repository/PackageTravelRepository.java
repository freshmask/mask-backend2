package com.mask.mask.repository;

import com.mask.mask.entity.PackageTravel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageTravelRepository extends JpaRepository<PackageTravel, String> {
    public List<PackageTravel> findPackageTravelByProductProductId(String productId);
    Page<PackageTravel> findAll(Specification<PackageTravel> productSpecification, Pageable pageable);
    public List<PackageTravel> findPackageTravelByIsActive(String isActive);
}

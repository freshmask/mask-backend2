package com.mask.mask.repository;

import com.mask.mask.entity.CategoryPA;
import com.mask.mask.entity.PackagePA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface CategoryPARepository extends JpaRepository<CategoryPA, String> {
    public List<CategoryPA> findCategoryPAByPackagePAPaId(String productId, Sort sort);
    Page<CategoryPA> findAll(Specification<CategoryPA> categoryPASpecification, Pageable pageable);
    public List<CategoryPA> findCategoryPAByPackagePAName (String packagePAName, Sort sort);
    public List<CategoryPA> findCategoryPAByIsActive(String isActive);

    public List<CategoryPA> findAllByPackagePAPaId(String packageId);
}

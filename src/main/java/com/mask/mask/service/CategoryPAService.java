package com.mask.mask.service;

import com.mask.mask.dto.CategoryPASearchDTO;
import com.mask.mask.dto.PackagePASearchDTO;
import com.mask.mask.entity.CategoryPA;
import com.mask.mask.entity.PackagePA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryPAService {
    public void addCategoryPA(CategoryPA categoryPA);
    public Page<CategoryPA> getCategoryPAInPage(Pageable pageable, CategoryPASearchDTO categoryPASearchDTO);
    public List<CategoryPA> getAllCategoryPA();
    public List<CategoryPA> getCategoryPAByPAId (String paId);
    public CategoryPA getCategoryPAById (String categoryId);
    public void editCategoryPA(CategoryPA categoryPA);
    public void deleteCategoryPAById (String categoryId);
    public List<CategoryPA> getCategoryPAByPackagePAName (String packagePAName);
    public List<CategoryPA> getCategoryPAByIsActive(String packageId, String isActive);
    public void changeStatusCategoryPAById(String id);
}

package com.mask.mask.service;

import com.mask.mask.dto.CategoryPASearchDTO;
import com.mask.mask.entity.CategoryPA;
import com.mask.mask.entity.PackagePA;
import com.mask.mask.entity.Product;
import com.mask.mask.exception.DataNotFoundException;
import com.mask.mask.exception.OtherDataNotFoundException;
import com.mask.mask.repository.CategoryPARepository;
import com.mask.mask.specification.CategoryPASpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryPAServiceImpl implements CategoryPAService {

    @Autowired
    CategoryPARepository categoryPARepository;

    @Autowired
    ProductService productService;

    @Autowired
    PackagePAService packagePAService;

    @Override
    public void addCategoryPA(CategoryPA categoryPA) {
        PackagePA packagePA = packagePAService.getPackagePAById(categoryPA.getPackagePA().getPaId());
        Product product = productService.getProductById(packagePA.getProduct().getProductId());
        productService.editProduct(product);
        categoryPARepository.save(categoryPA);
    }

    @Override
    public Page<CategoryPA> getCategoryPAInPage(Pageable pageable, CategoryPASearchDTO categoryPASearchDTO) {
        Specification<CategoryPA> categoryPASpecification= CategoryPASpecification.getSpecification(categoryPASearchDTO);
        return categoryPARepository.findAll(categoryPASpecification, pageable);
    }

    @Override
    public List<CategoryPA> getAllCategoryPA() {
        return categoryPARepository.findAll(Sort.by("days").ascending());
    }

    @Override
    public List<CategoryPA> getCategoryPAByPAId(String paId) {
        return categoryPARepository.findCategoryPAByPackagePAPaId(paId, Sort.by("days").ascending());
    }

    @Override
    public CategoryPA getCategoryPAById(String categoryId) {
        Optional<CategoryPA> categoryPAOptional = categoryPARepository.findById(categoryId);;
        if (categoryPAOptional.isPresent()){
            return categoryPAOptional.get();
        }
        throw new OtherDataNotFoundException(String.format(OtherDataNotFoundException.NOT_FOUND_MESSAGE, CategoryPA.class,categoryId));
    }

    @Override
    public void editCategoryPA(CategoryPA categoryPA) {
        PackagePA packagePA = packagePAService.getPackagePAById(categoryPA.getPackagePA().getPaId());
        Product product = productService.getProductById(packagePA.getProduct().getProductId());
        productService.editProduct(product);
        categoryPARepository.save(categoryPA);
    }

    @Override
    public void deleteCategoryPAById(String categoryId) {
        categoryPARepository.deleteById(categoryId);
    }

    @Override
    public List<CategoryPA> getCategoryPAByPackagePAName(String packagePAName) {
        return categoryPARepository.findCategoryPAByPackagePAName(packagePAName, Sort.by("days").ascending());
    }

    @Override
    public List<CategoryPA> getCategoryPAByIsActive(String packageId, String isActive) {
        List<CategoryPA> categoryPAList = categoryPARepository.findAllByPackagePAPaId(packageId);
        List<CategoryPA> categoryPAListActive = new ArrayList<>();
        for (CategoryPA categoryPA:categoryPAList) {
            if (categoryPA.getIsActive().equalsIgnoreCase(isActive)){
                categoryPAListActive.add(categoryPA);
            }
        }
        return categoryPAListActive;
    }

    @Override
    public void changeStatusCategoryPAById(String id) {
        CategoryPA categoryPA = categoryPARepository.findById(id).get();
        String status = categoryPA.getIsActive();
         if (status.equalsIgnoreCase("false")){
             categoryPA.setIsActive("true");
         } else if (status.equalsIgnoreCase("true")){
             categoryPA.setIsActive("false");
         }
        PackagePA packagePA = packagePAService.getPackagePAById(categoryPA.getPackagePA().getPaId());
        Product product = productService.getProductById(packagePA.getProduct().getProductId());
        productService.editProduct(product);
        categoryPARepository.save(categoryPA);
    }
}

package com.mask.mask.controller;

import com.mask.mask.dto.CategoryPASearchDTO;
import com.mask.mask.entity.CategoryPA;
import com.mask.mask.service.CategoryPAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryPAController {

    @Autowired
    CategoryPAService categoryPAService;

    @PostMapping("/categoryPA")
    public void addCategoryPA(@RequestBody CategoryPA categoryPA){
        categoryPAService.addCategoryPA(categoryPA);
    }

    @GetMapping("/categoriesPA")
    public Page<CategoryPA> searchCategoryPA (@RequestParam(name="page", defaultValue = "0") Integer page,
                                                    @RequestParam(name="size", defaultValue = "5") Integer sizePerPage,
                                                    @RequestParam(name = "days", required = false) Integer days,
                                                    @RequestParam(name = "price", required = false) Float price,
                                                    @RequestParam(name = "pricePromo", required = false) Float pricePromo,
                                                    @RequestParam(name = "voucher", required = false) String voucher){
        Pageable pageable= PageRequest.of(page,sizePerPage);
        CategoryPASearchDTO categoryPASearchDTO = new CategoryPASearchDTO(days, price, pricePromo, voucher);
        return categoryPAService.getCategoryPAInPage(pageable, categoryPASearchDTO);
    }

    @GetMapping("/categoriesPA/list")
    public List<CategoryPA> getAllListCategoryPA (){
        return categoryPAService.getAllCategoryPA();
    }

    @GetMapping("/categoryPA/list/{paId}")
    public List<CategoryPA> getCategoryPAByPAId(@PathVariable(name = "paId") String paId){
        return categoryPAService.getCategoryPAByPAId(paId);
    }

    @GetMapping("/categoryPA/{categoryId}")
    public CategoryPA getCategoryPA(@PathVariable(name = "categoryId") String categoryId){
        CategoryPA categoryPA = categoryPAService.getCategoryPAById(categoryId);
        return categoryPA;
    }

    @PutMapping("/categoryPA")
    public void editCategoryPA(@RequestBody CategoryPA categoryPA){
        categoryPAService.editCategoryPA(categoryPA);
    }

    @DeleteMapping("/categoryPA")
    public void deleteCategoryPAById (@RequestParam(name = "categoryId") String categoryId){
        categoryPAService.deleteCategoryPAById(categoryId);
    }

    @GetMapping("/categoryPA/list")
    public List<CategoryPA> getCategoryPAbyPackagePAName(@RequestParam String packagePAName){
        return categoryPAService.getCategoryPAByPackagePAName(packagePAName);
    }

    @GetMapping("/categoriesPA/{packageId}")
    public List<CategoryPA> getCategoryByisActive(@PathVariable (name = "packageId") String packageId, @RequestParam(name = "isActive") String isActive){
        return categoryPAService.getCategoryPAByIsActive(packageId, isActive);
    }

    @PutMapping("/categorystatus/{id}")
    public void setStatusCategory(@PathVariable String id){
        categoryPAService.changeStatusCategoryPAById(id);
    }
}

package com.mask.mask.controller;

import com.mask.mask.dto.PackagePASearchDTO;
import com.mask.mask.entity.PackagePA;
import com.mask.mask.service.PackagePAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PackagePAController {

    @Autowired
    PackagePAService packagePAService;

    @PostMapping("/packagePA")
    public void addPackagePA(@RequestBody PackagePA packagePA){
        packagePAService.addPackagePA(packagePA);
    }

    @GetMapping("/packagesPA")
    public Page<PackagePA> searchPackagePA (@RequestParam(name="page", defaultValue = "0") Integer page,
                                                    @RequestParam(name="size", defaultValue = "5") Integer sizePerPage,
                                                    @RequestParam(name = "name", required = false) String name,
                                                    @RequestParam(name = "compensation", required = false) Float compensation){
        Pageable pageable= PageRequest.of(page,sizePerPage);
        PackagePASearchDTO packagePASearchDTO = new PackagePASearchDTO(name, compensation);
        return packagePAService.getPackagePAInPage(pageable, packagePASearchDTO);
    }

    @GetMapping("/packagesPA/list")
    public List<PackagePA> getAllListPackagePA (){
        return packagePAService.getAllPackagePA();
    }

    @GetMapping("/packagePA/{paId}")
    public PackagePA getPackagePA(@PathVariable(name = "paId") String paId){
        PackagePA packagePA = packagePAService.getPackagePAById(paId);
        return packagePA;
    }

    @GetMapping("/packagePA/list/{productId}")
    public List<PackagePA> getPackagePAByProductId(@PathVariable(name = "productId") String productId){
        return packagePAService.getPackagePAByProductId(productId);
    }

    @PutMapping("/packagePA")
    public void editPackagePA(@RequestBody PackagePA packagePA){
        packagePAService.editPackagePA(packagePA);
    }

    @DeleteMapping("/packagePA")
    public void deletePackagePAById (@RequestParam(name = "paId") String paId){
        packagePAService.deletePackagePAById(paId);
    }
}

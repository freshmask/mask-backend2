package com.mask.mask.controller;

import com.mask.mask.dto.PackageTravelSearchDTO;
import com.mask.mask.entity.PackageTravel;
import com.mask.mask.service.PackageTravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PackageTravelController {

    @Autowired
    PackageTravelService packageTravelService;

    @PostMapping("/packageTravel")
    public void addPackageTravel( @RequestBody PackageTravel packageTravel){
        packageTravelService.addPackageTravel(packageTravel);
    }

    @GetMapping("/packagesTravel")
    public Page<PackageTravel> searchPackageTravel (@RequestParam(name="page", defaultValue = "0") Integer page,
                                                    @RequestParam(name="size", defaultValue = "5") Integer sizePerPage,
                                                    @RequestParam(name = "name", required = false) String name,
                                                    @RequestParam(name = "days", required = false) Integer days,
                                                    @RequestParam(name = "price", required = false) Float price,
                                                    @RequestParam(name = "pricePromo", required = false) Float pricePromo,
                                                    @RequestParam(name = "voucher", required = false) String voucher){
        Pageable pageable= PageRequest.of(page,sizePerPage);
        PackageTravelSearchDTO packageTravelSearchDTO = new PackageTravelSearchDTO(name, days, price, pricePromo, voucher);
        return packageTravelService.getPackageTravelInPage(pageable, packageTravelSearchDTO);
    }

    @GetMapping("/packageTravel/list")
    public List<PackageTravel> getAllListPackageTravel (){
        return packageTravelService.getAllPackageTravel();
    }

    @GetMapping("/packageTravel/list/{productId}")
    public List<PackageTravel> getPackageTravelByProductId(@PathVariable(name = "productId") String productId){
        return packageTravelService.getPackageTravelByProductId(productId);
    }

    @GetMapping("/packageTravel/{ptId}")
    public PackageTravel getPackageTravel(@PathVariable(name = "ptId") String ptId){
        PackageTravel packageTravel = packageTravelService.getPackageTravelById(ptId);
        return packageTravel;
    }

    @PutMapping("/packageTravel")
    public void editPackageTravel(@RequestBody PackageTravel packageTravel){
        packageTravelService.editPackageTravel(packageTravel);
    }

    @DeleteMapping("/packageTravel")
    public void deletePackageTravelById (@RequestParam(name = "ptId") String ptId){
        packageTravelService.deletePackageTravelById(ptId);
    }

    @GetMapping("/packagesTravel/{isActive}")
    public List<PackageTravel> getPackageTravelByisActive(@PathVariable String isActive){
        return packageTravelService.getPackageTravelByisActive(isActive);
    }

    @PutMapping("/travelstatus/{id}")
    public void setStatusPackageTravel(@PathVariable String id){
        packageTravelService.changeStatusPackageTravelById(id);
    }
}

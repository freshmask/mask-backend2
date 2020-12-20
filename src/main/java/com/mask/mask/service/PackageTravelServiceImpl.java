package com.mask.mask.service;

import com.mask.mask.dto.PackageTravelSearchDTO;
import com.mask.mask.entity.PackageTravel;
import com.mask.mask.entity.Product;
import com.mask.mask.exception.DataNotFoundException;
import com.mask.mask.exception.OtherDataNotFoundException;
import com.mask.mask.repository.PackageTravelRepository;
import com.mask.mask.specification.PackageTravelSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageTravelServiceImpl implements PackageTravelService {

    @Autowired
    PackageTravelRepository packageTravelRepository;

    @Autowired
    ProductService productService;

    @Override
    public void addPackageTravel(PackageTravel packageTravel) {
        Product product = productService.getProductById(packageTravel.getProduct().getProductId());
        productService.editProduct(product);
        packageTravelRepository.save(packageTravel);
    }

    @Override
    public Page<PackageTravel> getPackageTravelInPage(Pageable pageable, PackageTravelSearchDTO packageTravelSearchDTO) {
        Specification<PackageTravel> packageTravelSpecification= PackageTravelSpecification.getSpecification(packageTravelSearchDTO);
        return packageTravelRepository.findAll(packageTravelSpecification, pageable);
    }

    @Override
    public List<PackageTravel> getAllPackageTravel() {
        return packageTravelRepository.findAll(Sort.by("days").ascending());
    }

    @Override
    public List<PackageTravel> getPackageTravelByProductId(String productId) {
        return packageTravelRepository.findPackageTravelByProductProductId(productId);
    }

    @Override
    public PackageTravel getPackageTravelById(String ptId) {
        Optional<PackageTravel> packageTravelOptional = packageTravelRepository.findById(ptId);;
        if (packageTravelOptional.isPresent()){
            return packageTravelOptional.get();
        }
        throw new OtherDataNotFoundException(String.format(OtherDataNotFoundException.NOT_FOUND_MESSAGE,PackageTravel.class,ptId));
    }

    @Override
    public void editPackageTravel(PackageTravel packageTravel) {
        Product product = productService.getProductById(packageTravel.getProduct().getProductId());
        productService.editProduct(product);
        if (!packageTravelRepository.existsById(packageTravel.getPtId())) {
            throw new DataNotFoundException(String.format(DataNotFoundException.DATA_NOT_FOUND, packageTravel.getClass(), packageTravel.getPtId()));
        }
        packageTravelRepository.save(packageTravel);
    }

    @Override
    public void deletePackageTravelById(String ptId) {
        packageTravelRepository.deleteById(ptId);
    }

    @Override
    public List<PackageTravel> getPackageTravelByisActive(String isActive) {
        return packageTravelRepository.findPackageTravelByIsActive(isActive);
    }

    @Override
    public void changeStatusPackageTravelById(String id) {
        PackageTravel packageTravel = packageTravelRepository.findById(id).get();
        String status = packageTravel.getIsActive();
        if (status.equalsIgnoreCase("false")){
            packageTravel.setIsActive("true");
        } else if (status.equalsIgnoreCase("true")){
            packageTravel.setIsActive("false");
        }
        Product product = productService.getProductById(packageTravel.getProduct().getProductId());
        productService.editProduct(product);
        packageTravelRepository.save(packageTravel);
    }
}

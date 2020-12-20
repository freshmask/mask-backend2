package com.mask.mask.service;

import com.mask.mask.dto.PackagePASearchDTO;
import com.mask.mask.entity.PackagePA;
import com.mask.mask.entity.Product;
import com.mask.mask.exception.DataNotFoundException;
import com.mask.mask.exception.OtherDataNotFoundException;
import com.mask.mask.repository.PackagePARepository;
import com.mask.mask.specification.PackagePASpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class PackagePAServiceImpl implements PackagePAService {

    @Autowired
    PackagePARepository packagePARepository;

    @Autowired
    ProductService productService;

    @Override
    public void addPackagePA(PackagePA packagePA) {
        Product product = productService.getProductById(packagePA.getProduct().getProductId());
        productService.editProduct(product);
        packagePARepository.save(packagePA);
    }

    @Override
    public Page<PackagePA> getPackagePAInPage(Pageable pageable, PackagePASearchDTO packagePASearchDTO) {
        Specification<PackagePA> packagePASpecification= PackagePASpecification.getSpecification(packagePASearchDTO);
        return packagePARepository.findAll(packagePASpecification, pageable);
    }

    @Override
    public List<PackagePA> getAllPackagePA() {
        return packagePARepository.findAll(Sort.by("compensation").ascending());
    }

    @Override
    public PackagePA getPackagePAById(String paId) {
        Optional<PackagePA> productOptional = packagePARepository.findById(paId);;
        if (productOptional.isPresent()){
            return productOptional.get();
        }
        throw new OtherDataNotFoundException(String.format(OtherDataNotFoundException.NOT_FOUND_MESSAGE,PackagePA.class,paId));
    }

    @Override
    public List<PackagePA> getPackagePAByProductId(String productId) {
        return packagePARepository.findPackagePAByProductProductId(productId);
    }

    @Override
    public void editPackagePA(PackagePA packagePA) {
        Product product = productService.getProductById(packagePA.getProduct().getProductId());
        productService.editProduct(product);
        packagePARepository.save(packagePA);
    }

    @Override
    public void deletePackagePAById(String paId) {
        packagePARepository.deleteById(paId);
    }
}

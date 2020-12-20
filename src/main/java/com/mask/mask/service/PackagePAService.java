package com.mask.mask.service;

import com.mask.mask.dto.PackagePASearchDTO;
import com.mask.mask.dto.PackageTravelSearchDTO;
import com.mask.mask.entity.PackagePA;
import com.mask.mask.entity.PackageTravel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PackagePAService {
    public void addPackagePA(PackagePA packagePA);
    public Page<PackagePA> getPackagePAInPage(Pageable pageable, PackagePASearchDTO packagePASearchDTO);
    public List<PackagePA> getAllPackagePA();
    public PackagePA getPackagePAById (String paId);
    public List<PackagePA> getPackagePAByProductId (String productId);
    public void editPackagePA(PackagePA packagePA);
    public void deletePackagePAById (String paId);
}

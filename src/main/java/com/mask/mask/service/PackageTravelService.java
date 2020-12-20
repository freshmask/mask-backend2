package com.mask.mask.service;

import com.mask.mask.dto.PackageTravelSearchDTO;
import com.mask.mask.entity.PackageTravel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PackageTravelService {
    public void addPackageTravel(PackageTravel packageTravel);
    public Page<PackageTravel> getPackageTravelInPage(Pageable pageable, PackageTravelSearchDTO packageTravelSearchDTO);
    public List<PackageTravel> getAllPackageTravel();
    public List<PackageTravel> getPackageTravelByProductId (String productId);
    public PackageTravel getPackageTravelById (String ptId);
    public void editPackageTravel(PackageTravel packageTravel);
    public void deletePackageTravelById (String ptId);
    public List<PackageTravel> getPackageTravelByisActive(String isActive);
    public void changeStatusPackageTravelById(String id);
}

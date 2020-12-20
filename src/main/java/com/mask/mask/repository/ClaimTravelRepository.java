package com.mask.mask.repository;

import com.mask.mask.entity.ClaimTravel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimTravelRepository extends JpaRepository<ClaimTravel, String> {
}

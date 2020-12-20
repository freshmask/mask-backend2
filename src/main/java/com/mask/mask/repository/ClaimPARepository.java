package com.mask.mask.repository;

import com.mask.mask.entity.ClaimPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimPARepository extends JpaRepository<ClaimPA, String> {

}

package com.mask.mask.repository;

import com.mask.mask.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,String>, JpaSpecificationExecutor<Users> {

    Optional<Users> findUsersByEmail(String email);
    public Users findUsersByRole(String role);
}

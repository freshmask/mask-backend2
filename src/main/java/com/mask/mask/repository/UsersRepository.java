package com.mask.mask.repository;

import com.mask.mask.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
//    Optional<Users> findUserByUsername(String username);
    Optional<Users> findUserByEmail(String email);

    public Page<Users> getUserByRole(String role, Pageable pageable);

//    public Users getUserByUsername(String username);
    Users findByEmailIgnoreCase(String email);
    Users findUsersByEmail (String email);
}

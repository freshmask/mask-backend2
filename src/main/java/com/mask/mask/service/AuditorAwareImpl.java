package com.mask.mask.service;

import com.mask.mask.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    UsersService usersService;

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()){
            return Optional.of("first sign up");
        }
        return Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}

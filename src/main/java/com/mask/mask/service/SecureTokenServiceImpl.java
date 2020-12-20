package com.mask.mask.service;

import com.mask.mask.entity.SecureToken;
import com.mask.mask.entity.Users;
import com.mask.mask.repository.SecureTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecureTokenServiceImpl implements SecureTokenService {

    @Autowired
    SecureTokenRepository secureTokenRepository;

    @Autowired
    EmailSenderCore emailSenderCore;

    @Override
    public void getSecureToken(Users users) {
        SecureToken secureToken = new SecureToken(users);
        secureTokenRepository.save(secureToken);
        emailSenderCore.contextLoads(users, secureToken);
    }
}

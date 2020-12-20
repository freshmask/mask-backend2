package com.mask.mask.service;

import com.mask.mask.entity.Users;

import javax.mail.MessagingException;
import java.io.IOException;

public interface SecureTokenService {
    public void getSecureToken (Users users) throws IOException, MessagingException;
}

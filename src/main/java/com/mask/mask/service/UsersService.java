package com.mask.mask.service;

import com.mask.mask.entity.SecureToken;
import com.mask.mask.entity.Users;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UsersService {
    public Users saveUsers(Users users);
    public void updateUsers(String id, Users users);
    public void editUsers(String id, Users users);
    public void deleteUsersById(String id);
    public Page<Users> getAllUsersPerPage(Pageable pageable);
    public List<Users> getAllUsers();
    public Users getUsersById(String id);
    public void setStatus(String id);
    public Page<Users> getUsersByRole(String role, Pageable pageable);
    public void registerUser(Users users);

    public SecureToken getByConfirmToken (String token);
    public Users getUsersByEmailBasedToken (SecureToken secureToken);
    public Users getUsersByEmail (String email);

    public void deleteSecureToken (String tokenId);
    public Users forgotPasswordUser (String email) throws IOException, MessagingException;

    public Users editPhotoByUserId (String id, Users users);


    public void changePassword (String id, String currentPassword, Users users) throws IOException, MessagingException;
}

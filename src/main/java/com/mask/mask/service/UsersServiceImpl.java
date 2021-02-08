package com.mask.mask.service;

import com.mask.mask.FileStorageProperties;
import com.mask.mask.entity.CustomerPA;
import com.mask.mask.entity.SecureToken;
import com.mask.mask.entity.Users;
import com.mask.mask.exception.*;
import com.mask.mask.repository.SecureTokenRepository;
import com.mask.mask.repository.UsersRepository;
import com.mask.mask.security.UsersServiceAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository userRepository;

    @Autowired
    SecureTokenRepository secureTokenRepository;

    @Autowired
    forgotPasswordService forgotPasswordService;

    @Autowired
    UsersServiceAuth usersServiceAuth;

    @Autowired
    EmailSenderCore emailSenderCore;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void changePassword(String id, String currentPassword, Users users) throws IOException, MessagingException {
        Users users1 = userRepository.findById(id).get();
        Boolean result = passwordEncoder.matches(currentPassword, users1.getPassword());
        if (result){
            users1.setPassword(users.getPassword());
            usersServiceAuth.registerUser(users1);
        } else {
            throw new PasswordNotMatchException("Password didn't match");
        }
    }

    @Override
    public Users saveUsers(Users users) {
        users.setVersion(0);
        return userRepository.save(users);
    }

    @Override
    public void updateUsers(String id, Users users) {
        Users users1 = userRepository.findById(id).get();
        users1.setName(users.getName());
        users1.setEmail(users.getEmail());
        users1.setIdCardNo(users.getIdCardNo());
        users1.setPassword(users.getPassword());
        users1.setBirthdate(users.getBirthdate());
        users1.setGender(users.getGender());
        users1.setRole(users.getRole());
        users1.setIsActive(users.getIsActive());
        users1.setPhoneNumber(users.getPhoneNumber());
        users1.setFingerData(users.getFingerData());
        if (users.getFileName() != null) {
            users1.setFileName(users.getFileName());
        }
        users1.setVersion(users1.getVersion() + 1);
        userRepository.save(users1);
    }

    @Override
    public void deleteUsersById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public void editUsers(String id, Users users) {
        if (!userRepository.existsById(users.getId())) {
            throw new DataNotFoundException(String.format(DataNotFoundException.DATA_NOT_FOUND, Users.class, users.getId()));
        }
        Users users1 = userRepository.findById(users.getId()).get();
        users.setVersion(users1.getVersion() + 1);
        userRepository.save(users);
    }

    @Override
    public Page<Users> getAllUsersPerPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUsersById(String id) {
        return userRepository.findById(id).get();
    }


    @Override
    public void setStatus(String id) {
        Users user = getUsersById(id);
        String status = user.getIsActive();
        if (status.equals("false")){
            user.setIsActive("true");
        } else if (status.equals("true")) {
            user.setIsActive("false");
        }
        user.setVersion(user.getVersion() + 1);
        userRepository.save(user);
    }

    @Override
    public Page<Users> getUsersByRole(String role, Pageable pageable) {
        return userRepository.getUserByRole(role, pageable);
    }

    @Override
    public Page<Users> findUserByRole(String role, Pageable pageable) {
        return null;
    }

    @Override
    public void registerUser(Users users) {
        Users users1 = userRepository.findById(users.getId()).get();
        users.setVersion(users1.getVersion() + 1);
        userRepository.save(users);
    }

    @Override
    public SecureToken getByConfirmToken(String token) {
        SecureToken secureToken = secureTokenRepository.findByConfirmToken(token);
        return secureToken;
    }

    @Override
    public Users getUsersByEmailBasedToken(SecureToken secureToken) {
        Users users = userRepository.findByEmailIgnoreCase(secureToken.getUsers().getEmail());
        return users;
    }

    @Override
    public Users getUsersByEmail(String email) {
        Users users = userRepository.findUsersByEmail(email);
        return users;
    }

    @Override
    public void deleteSecureToken(String tokenId) {
        secureTokenRepository.deleteById(tokenId);
    }

    @Override
    public Users forgotPasswordUser(String email) throws IOException, MessagingException {
        Optional<Users> usersOptional = userRepository.findUserByEmail(email);
        if (usersOptional.isPresent()){
            Users users = usersOptional.get();
            String generateTempPass = forgotPasswordService.generateTempPassword();
            users.setPassword(generateTempPass);
            usersServiceAuth.registerUser(users);
            emailSenderCore.forgotPassLoads(users, generateTempPass);
            return users;
        }
        throw new OtherDataNotFoundException(String.format(OtherDataNotFoundException.NOT_FOUND_MESSAGE,Users.class,email));
    }

    @Override
    public Users editPhotoByUserId(String id, Users users) {
        Users users1 = userRepository.findById(id).get();
        users1.setFileName(users.getFileName());
        users1.setVersion(users1.getVersion() + 1);
        return userRepository.save(users1);
    }
}

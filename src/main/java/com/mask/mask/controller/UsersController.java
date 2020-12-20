package com.mask.mask.controller;

import com.mask.mask.entity.Users;
import com.mask.mask.security.SecurityConfiguration;
import com.mask.mask.security.UsersServiceAuth;
import com.mask.mask.service.*;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@RestController
public class UsersController {

    @Value("${document-user}")
    String userProfile;

    @Autowired
    UsersService usersService;

    @Autowired
    UsersServiceAuth usersServiceAuth;

    @Autowired
    SecurityConfiguration securityConfiguration;


    @PostMapping("/user")
    public Users saveUser(@RequestPart MultipartFile file,
                         @RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String idCardNo,
                         @RequestParam String password,
                         @RequestParam Date birthdate,
                         @RequestParam String gender,
                         @RequestParam String role,
                         @RequestParam String isActive,
                         @RequestParam String phoneNumber,
                         @RequestParam String fingerData) {
        try{
            file.transferTo(Paths.get(userProfile, "Photo-" + name + "."+ FilenameUtils.getExtension(file.getOriginalFilename())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = StringUtils.cleanPath("Photo-" + name + "."+ FilenameUtils.getExtension(file.getOriginalFilename()));
        Users users = new Users(name, email, idCardNo, password, birthdate, gender, role, isActive, phoneNumber, fingerData, filename);
        return usersService.saveUsers(users);
    }


    @DeleteMapping("/user")
    public void deleteUserById(@RequestParam(name = "id") String id) {
        usersService.deleteUsersById(id);
    }

    @PutMapping("/editUsers/{id}")
    public void editUser(@PathVariable String id,
                         @RequestBody Users users){
        usersService.editUsers(id, users);
    }

    @PutMapping("/updateUser/{id}")
    public void updateUser(@PathVariable String id,
                           @RequestParam (required = false) MultipartFile file,
                           @RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String idCardNo,
                           @RequestParam String password,
                           @RequestParam Date birthdate,
                           @RequestParam String gender,
                           @RequestParam String role,
                           @RequestParam String isActive,
                           @RequestParam String phoneNumber,
                           @RequestParam String fingerData) {
        try{
            if (file != null) {
                file.transferTo(Paths.get(userProfile, "Photo-" + file.getOriginalFilename() + "."+ FilenameUtils.getExtension(file.getOriginalFilename())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file != null){
            String filename = StringUtils.cleanPath("Photo-" + file.getOriginalFilename() + "."+ FilenameUtils.getExtension(file.getOriginalFilename()));
            Users users1 = new Users(name, email, idCardNo, password, birthdate, gender, role, isActive, phoneNumber, fingerData, filename);
            usersService.updateUsers(id, users1);
        } else {
            Users users1 = new Users(name, email, idCardNo, password, birthdate, gender, role, isActive, phoneNumber, fingerData);
            usersService.updateUsers(id, users1);
        }
    }

    @PutMapping("/updatePhoto/{id}")
    public Users updatePhoto(@PathVariable String id,
                           @RequestPart MultipartFile file) {
        try{
            file.transferTo(Paths.get(userProfile, "Photo-" +file.getOriginalFilename()+ "."+ FilenameUtils.getExtension(file.getOriginalFilename())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = StringUtils.cleanPath("Photo-" +file.getOriginalFilename()+ "."+ FilenameUtils.getExtension(file.getOriginalFilename()));
        Users users1 = new Users(filename);
        return usersService.editPhotoByUserId(id, users1);
    }



    @GetMapping("/users")
    public Page<Users> getAllUserPerPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                         @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return usersService.getAllUsersPerPage(pageable);
    }

    @GetMapping("/user/list")
    public List<Users> getAllUsers(){
        return usersService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public Users getUserById(@PathVariable(name = "id") String id) {
        Users user = usersService.getUsersById(id);
        return user;
    }

    @GetMapping("/getUserByRole/{role}")
    public Page<Users> getUserByRole(@PathVariable String role,
                                     @RequestParam(name = "page", defaultValue = "0") Integer page,
                                     @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return usersService.getUsersByRole(role, pageable);
    }

    @PutMapping("/user/{id}")
    public void updateStatus(@PathVariable String id){
        usersService.setStatus(id);
    }

    @PostMapping("/signup")
    public void signup(@RequestBody Users users) throws IOException, MessagingException {
        usersServiceAuth.registerUser(users);
    }

    @PostMapping("/changePassword/{id}")
    public void changePassword(@PathVariable(name = "id") String id, @RequestParam(name = "currentPassword") String currentPassword, @RequestBody Users users) throws IOException, MessagingException {
        usersService.changePassword(id, currentPassword, users);
    }

    @PostMapping("/forgotPassword")
    public Users forgotPassword (@RequestParam(name = "email", required = false) String email) throws IOException, MessagingException {
        Users users = usersService.forgotPasswordUser(email);
        return users;
    }
}

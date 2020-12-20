package com.mask.mask.security;

import com.mask.mask.entity.Users;
import com.mask.mask.entity.UsersHelper;
import com.mask.mask.repository.UserRepository;
import com.mask.mask.service.SecureTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceAuth implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SecureTokenService secureTokenService;

//    @Override
//    public UsersHelper loadUserByUsername(final String username) throws UsernameNotFoundException {
//        UsersPojo usersPojo = null;
//        try {
//            usersPojo = usersDBQuery.getUserDetails(username);
//            UsersHelper userDetail = new UsersHelper(usersPojo);
//            return userDetail;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new UsernameNotFoundException("User " + username + " was not found in the database");
//        }
//    }
//      @Override
//      public UsersHelper loadUserByUsername(final String username) throws UsernameNotFoundException {
//          Collection<GrantedAuthority> listOfgrantedAuthorities = new ArrayList<>();
//          Optional<Users> user = userRepository.findUsersByUsername(username);
//
//                UsersHelper userDetail = new UsersHelper(user.get());
//                return userDetail;
//
//          if (list.size() > 0) {
//              Users user = new Users();
////              GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
//              GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.setRole());
//              listOfgrantedAuthorities.add(grantedAuthority);
//              list.get(0).setListOfgrantedAuthorities(listOfgrantedAuthorities);
//              return list.get(0);
//          }
//          return null;
//      }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> listOfgrantedAuthorities = new ArrayList<>();
        System.out.println(username);
        try{
            Optional<Users> optionalUser = userRepository.findUsersByEmail(username);
            if (!optionalUser.isPresent()){
                throw new UsernameNotFoundException(username+"Not Found!!");
            }

            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(optionalUser.get().getRole());
            listOfgrantedAuthorities.add(grantedAuthority);

            UsersHelper userDetail = new UsersHelper(optionalUser.get());
            return userDetail;

        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
    }

    public void registerUser(Users user) throws IOException, MessagingException {
        if (user.getId() != null) {
            Users existUsers = userRepository.findById(user.getId()).get();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setVersion(existUsers.getVersion() + 1);
            userRepository.save(user);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setVersion(0);
            user.setIsActive("false");
            userRepository.save(user);
            if (user.getRole().equalsIgnoreCase("03")) {
                secureTokenService.getSecureToken(user);
            }
        }
    }

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public Users getUser(String username){
        Optional<Users> usersOptional = userRepository.findUsersByEmail(username);
        return usersOptional.get();
    }

}

package com.mask.mask.controller;

import com.mask.mask.entity.SecureToken;
import com.mask.mask.entity.Users;
import com.mask.mask.service.EmailSenderCore;
import com.mask.mask.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class ConfirmAccount {

    @Autowired
    UsersService usersService;

    @Autowired
    EmailSenderCore emailSenderCore;

    @RequestMapping(value = "/confirm-account", method = RequestMethod.GET)
    public String confirmUserAccount (@RequestParam("token") String confirmToken, HttpServletRequest request, Model model) throws IOException, MessagingException {
        SecureToken token = usersService.getByConfirmToken(confirmToken);
        model.addAttribute("token", token);
        String result = "";
        if (token != null){
            Users users = usersService.getUsersByEmailBasedToken(token);
            users.setIsActive("true");
            usersService.updateUsers(users.getId(), users);
            result = "verifiedPage";
            SecureToken secureToken = usersService.getByConfirmToken(token.getConfirmToken());
            usersService.deleteSecureToken(secureToken.getTokenId());
            emailSenderCore.notifAccountActivatedLoads(users);
        } else {
            result = "accountHasActivated";
        }
        return result;
    }
}

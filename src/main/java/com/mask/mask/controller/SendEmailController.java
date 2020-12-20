package com.mask.mask.controller;

import com.mask.mask.service.SendingEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

@RestController
public class SendEmailController {
    
    @Autowired
    SendingEmailService sendingEmailService;

    @RequestMapping(value = "/sendemail")
    public String send() throws AddressException, MessagingException, IOException {
        //sendEmail();
//        maskApplication.sendEmailWithAttachment();
        sendingEmailService.sendEmailWithAttachment();
        return "Email sent successfully";
    }

//    @RequestMapping(value = "/sendemailverf")
//    public String sendVerf() throws AddressException, MessagingException, IOException {
//        //sendEmail();
////        maskApplication.sendEmailWithAttachment();
//        sendingEmailService.sendEmailVerfUser();
//        return "Email sent successfully";
//    }

//    @Autowired
//    MaskApplication maskApplication;
//
//        public String send() throws AddressException, MessagingException, IOException {
//        //sendEmail();
////        maskApplication.sendEmailWithAttachment();
//        maskApplication.sendEmailWithAttachment();
//        return "Email sent successfully";
//    }


}

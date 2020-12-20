package com.mask.mask.service;

import com.mask.mask.entity.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailSenderService {

    @Value("${document-policy}")
    String documentPolicy;

    @Value("${document-wording}")
    String documentWording;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;


    public void sendVerfMessage(Mail mail, Map<String, Object> model) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Context context = new Context();
        context.setVariables(model);
        System.out.println(context);
        String html = templateEngine.process("accountVerificationEmail", context);

        try {
            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        emailSender.send(message);
    }

    public void sendForgotMessage(Mail mail, Map<String, Object> model) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Context context = new Context();
        context.setVariables(model);
        System.out.println(context);
        String html = templateEngine.process("forgotPasswordEmail", context);

        try {
            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        emailSender.send(message);
    }

    public void sendNotifActivMessage(Mail mail, Map<String, Object> model) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Context context = new Context();
        context.setVariables(model);
        System.out.println(context);
        String html = templateEngine.process("NotifEmailActivated", context);

        try {
            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        emailSender.send(message);
    }

    public void sendPolisPA(Mail mail, Map<String, Object> model, String polisNumber) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process("EmailSendPolis", context);

        try {
            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        FileSystemResource fileSystemResource = new FileSystemResource(documentPolicy + polisNumber + ".pdf");
        try {
            helper.addAttachment(polisNumber + ".pdf", fileSystemResource);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        FileSystemResource fileSystemResourceWording = new FileSystemResource(documentWording+"Wording-Asuransi-Personal-Accident-TC.pdf");
        try {
            helper.addAttachment("Wording-Asuransi-Personal-Accident-TC.pdf", fileSystemResourceWording);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        emailSender.send(message);
    }

    public void sendPolisTravel(Mail mail, Map<String, Object> model, String idPolisTravel) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Context context = new Context();
        context.setVariables(model);
        System.out.println(context);
        String html = templateEngine.process("EmailSendPolis", context);

        try {
            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        FileSystemResource fileSystemResource = new FileSystemResource(documentPolicy + idPolisTravel + ".pdf");
        try {
            helper.addAttachment(idPolisTravel+".pdf", fileSystemResource);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        FileSystemResource fileSystemResourceWording = new FileSystemResource(documentWording+"Wording-Asuransi-Travel.pdf");
        try {
            helper.addAttachment("Wording-Asuransi-Travel.pdf", fileSystemResourceWording);
        } catch (MessagingException e) {
            e.printStackTrace();
        }



        emailSender.send(message);
    }

    public void sendPolisPAR(Mail mail, Map<String, Object> model, String idPolisPAR) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process("EmailSendPolis", context);

        try {
            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        FileSystemResource fileSystemResource = new FileSystemResource(documentPolicy + idPolisPAR + ".pdf");
        try {
            helper.addAttachment(idPolisPAR+".pdf", fileSystemResource);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        emailSender.send(message);
    }

    public void sendClaimMessage(Mail mail, Map<String, Object> model) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,
            MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
            StandardCharsets.UTF_8.name());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process("ClaimNotification", context);

        try {
            helper.setTo(new String[]{mail.getTo(), mail.getCc()});
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        emailSender.send(message);
    }

    public void sendRenewalNotified(Mail mail, Map<String, Object> model) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process("renewalNotifiedEmail", context);

        try {
            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        emailSender.send(message);
    }
}

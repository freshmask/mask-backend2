package com.mask.mask.service;

import com.mask.mask.entity.SecureToken;
import com.mask.mask.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class SendingEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("fadlym22@gmail.com");

        msg.setSubject("Testing sending email");
        msg.setText("haloo world");


        javaMailSender.send(msg);

    }

    public void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo("1309rizki@gmail.com");

        helper.setSubject("Email From M-ASK Askrindo");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h4>M-ASK app Asuransi kredit indonesia (ASKRINDO)</h4>\n" +
                "<br> \n" +
                "<p>Semalat akun M-ASK anda sudah di aktifkan</p>\n" +
                "<br>\n" +
                "<p>Terimakasih</p>", true);

        // hard coded a file path
//        FileSystemResource file = new FileSystemResource(new File("C:/Users\\1302143\\Desktop\\ssl4.png"));
//
//        helper.addAttachment("ssl4.png", file);

        javaMailSender.send(msg);

    }

    public void sendEmailVerfUser(Users users, SecureToken secureToken) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(users.getEmail());

        helper.setSubject("Email From M-ASK Askrindo");

        helper.setText(
                "<img class='mb-4' src='https://askrindo.co.id/o/askrindo-theme/images/logo-askrindo-ifg.png' alt='' width='250' height='100' style='align-items: center; align-self: center; align-content: center;'>" +
                        "<p>Hi, " + users.getName() + "</p>\n" +
                        "<p>Harap verifikasi alamat email Anda untuk melengkapi Akun M-Ask Anda.</p>\n" +
                        "<a style=" + "text-align: center;" + " href='http://localhost:8080/confirm-account?token=" + secureToken.getConfirmToken() + "'" + "> Verify Account</a>" +
                        "<br>\n" +
                        "<p>Terima kasih atas kepercayaan anda di M-Ask App</p>" +
                        "<br>\n" +
                        "<p>Salam Hangat,</p>" +
                        "<br>\n" +
                        "<p>Client Service Team</p>" +
                        "<p>PT Asuransi Kredit Indonesia</p>" +
                        "<p>Jl. Angkasa Blok B-9 Kav No.8,kemayoran</p>" +
                        "<p>Jakarta pusat – 10610</p>" +
                        "<br>\n" +
                        "<p style='text-align: center;'>Untuk info lebih lanjut, mohon kunjungi</p>" + "\n" +
                        "" +
                        "<a style='text-align: center;' href='https://askrindo.co.id'>https://askrindo.co.id</a>" +
                        "", true);

        // hard coded a file path
//        FileSystemResource file = new FileSystemResource(new File("C:/Users\\1302143\\Desktop\\ssl4.png"));
//
//        helper.addAttachment("ssl4.png", file);

        javaMailSender.send(msg);

    }

    public void sendEmailForgotPassword(Users users, String generateTempPass) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(users.getEmail());
        helper.setSubject("[M-ASK] Your new temporary password");
        helper.setText(
                "<img class='mb-4' src='https://askrindo.co.id/o/askrindo-theme/images/logo-askrindo-ifg.png' alt='' width='250' height='100' style='align-items: center; align-self: center; align-content: center;'>" +
                        "<p>Hi, " + users.getName() + "</p>\n" +
                        "<p>Berikut adalah akun dengan password baru untuk login,  Kami sarankan Anda untuk mengganti password ini setelah dapat mengakses aplikasi M-Ask</p>\n" +
                        "<br>\n" +
                        "E-mail address: "+ users.getEmail() + "\n" +
                        "<br>\n" +
                        "Password : "+ generateTempPass + "\n" +
                        "<p>Jika ada pertanyaan lebih lanjut, mohon email Tim Client Service kami ke askrindo@askrindo.co.id atau telepon ke  021-6546471/72.</p>" + "\n" +
                        "<br>\n" +
                        "<p>Terima kasih atas kepercayaan anda di M-Ask App</p>" +
                        "<br>\n" +
                        "<p>Salam Hangat,</p>" +
                        "<br>\n" +
                        "<p>Client Service Team</p>" +
                        "<p>PT Asuransi Kredit Indonesia</p>" +
                        "<p>Jl. Angkasa Blok B-9 Kav No.8,kemayoran</p>" +
                        "<p>Jakarta pusat – 10610</p>" +
                        "<br>\n" +
                        "<p style='text-align: center;'>Untuk info lebih lanjut, mohon kunjungi</p>" + "\n" +
                        "" +
                        "<a style='text-align: center;' href='https://askrindo.co.id'>https://askrindo.co.id</a>" +
                        "", true);

        javaMailSender.send(msg);

    }
}

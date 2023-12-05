package com.br.digital_hoteis.domain.service.impl;


import com.br.digital_hoteis.domain.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service("emailService")
public class EmailServiceImpl implements EmailService {
    @Autowired
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

//    private final static Logger LOGGER = LoggerFactory
//            .getLogger(EmailServiceImpl.class);
//
//    private final JavaMailSender mailSender;
//
//    @Override
//    @Async
//    public void sendEmail(String to, String email) {
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper helper =
//                new MimeMessageHelper(mimeMessage, "utf-8");
//        try {
//            helper.setText(email, true);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            helper.setTo(to);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            helper.setSubject("Confirme seu email");
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            helper.setFrom("sacdigitalhoteis@gmail.com");
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//        mailSender.send(mimeMessage);
//    }
}
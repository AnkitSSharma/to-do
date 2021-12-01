package com.example.ToDo.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleEmail(String toEmail, String subject,String body ){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("ankit.sharma@unicommerce.com");
        message.setText(body);
        message.setSubject(subject);
        message.setTo(toEmail);
        javaMailSender.send(message);
    }
}

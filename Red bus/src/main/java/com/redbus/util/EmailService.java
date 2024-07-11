package com.redbus.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailWithAttachment(String to, String subject, String body, String attachmentFileName, byte[] attachment) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);

            // Attach the PDF file
            helper.addAttachment(attachmentFileName, new ByteArrayResource(attachment));

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }
}

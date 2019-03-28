package com.njtech.smartuniversity.service.impl;

import com.njtech.smartuniversity.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Created by ritchie on 7/2/18
 */
@Component
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mJavaMailSender;

    @Value("${mail.fromMail.addr}")
    private String from;


    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mJavaMailSender.send(message);
        } catch (Exception e) {
        }
    }
}

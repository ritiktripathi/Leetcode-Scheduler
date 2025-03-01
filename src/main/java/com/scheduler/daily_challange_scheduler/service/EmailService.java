package com.scheduler.daily_challange_scheduler.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendEmail(String to, String subject, String body) {
        try {
            if(javaMailSender != null) {
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(to);
                mail.setSubject(subject);
                mail.setText(body);
                javaMailSender.send(mail);
            }
            else {
                log.error("JAVA MAIL SERVICE IS NULL");
                throw new NullPointerException();
            }
        } catch (Exception e) {
            log.error("Exception while sendEmail ", e);
        }
    }


}
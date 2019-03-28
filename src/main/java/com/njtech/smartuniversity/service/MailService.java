package com.njtech.smartuniversity.service;

/**
 * Created by ritchie on 7/2/18
 */
public interface MailService {

    void sendSimpleMail(String to, String subject, String content);

}

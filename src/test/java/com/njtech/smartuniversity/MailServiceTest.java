package com.njtech.smartuniversity;

import com.njtech.smartuniversity.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mMailService;


    @Test
    public void testSimpleMail() throws Exception {
        mMailService.sendSimpleMail("1169532212@qq.com", "test simple mail", " hello this is simple mail");
    }
}

package com.njtech.smartuniversity.service;

import com.njtech.smartuniversity.bean.EmailInfoBean;

/**
 * Created by ritchie on 7/2/18
 */
public interface EmailInfoService {

    EmailInfoBean addEmailInfo(String email, String code);

    EmailInfoBean selectEmailInfo(String email, String code);

    EmailInfoBean selectEmailInfoByEmail(String email);

    EmailInfoBean updateEmailInfo(EmailInfoBean emailInfoBean);

}

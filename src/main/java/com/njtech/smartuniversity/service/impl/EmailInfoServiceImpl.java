package com.njtech.smartuniversity.service.impl;

import com.njtech.smartuniversity.bean.EmailInfoBean;
import com.njtech.smartuniversity.bean.EmailInfoBeanExample;
import com.njtech.smartuniversity.dao.EmailInfoBeanMapper;
import com.njtech.smartuniversity.service.EmailInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ritchie on 7/2/18
 */
@Service
public class EmailInfoServiceImpl implements EmailInfoService {

    @Autowired
    EmailInfoBeanMapper mEmailInfoBeanMapper;

    @Override
    public EmailInfoBean addEmailInfo(String email, String code) {
        EmailInfoBean bean = new EmailInfoBean();
        bean.setEmail(email);
        bean.setCode(code);

        mEmailInfoBeanMapper.insert(bean);
        EmailInfoBeanExample example = new EmailInfoBeanExample();
        EmailInfoBeanExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email);
        criteria.andCodeEqualTo(code);
        List<EmailInfoBean> emailInfoBeanList = mEmailInfoBeanMapper.selectByExample(example);
        if (emailInfoBeanList != null && emailInfoBeanList.size() > 0) {
            bean = emailInfoBeanList.get(0);
            return bean;
        }
        return null;
    }

    @Override
    public EmailInfoBean selectEmailInfo(String email, String code) {
        EmailInfoBeanExample example = new EmailInfoBeanExample();
        EmailInfoBeanExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email);
        criteria.andCodeEqualTo(code);
        List<EmailInfoBean> emailInfoBeanList = mEmailInfoBeanMapper.selectByExample(example);
        if (emailInfoBeanList != null && emailInfoBeanList.size() > 0) {
            EmailInfoBean bean = emailInfoBeanList.get(0);
            return bean;
        }
        return null;
    }

    @Override
    public EmailInfoBean selectEmailInfoByEmail(String email) {
        EmailInfoBeanExample example = new EmailInfoBeanExample();
        EmailInfoBeanExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email);
        List<EmailInfoBean> emailInfoBeanList = mEmailInfoBeanMapper.selectByExample(example);
        if (emailInfoBeanList != null && emailInfoBeanList.size() > 0) {
            EmailInfoBean bean = emailInfoBeanList.get(0);
            return bean;
        }
        return null;
    }

    @Override
    public EmailInfoBean updateEmailInfo(EmailInfoBean emailInfoBean) {
        int id = emailInfoBean.getId();
        mEmailInfoBeanMapper.updateByPrimaryKey(emailInfoBean);
        return mEmailInfoBeanMapper.selectByPrimaryKey(id);
    }
}

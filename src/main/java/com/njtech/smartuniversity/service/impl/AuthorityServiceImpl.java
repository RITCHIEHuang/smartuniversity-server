package com.njtech.smartuniversity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njtech.smartuniversity.bean.AuthorityBean;
import com.njtech.smartuniversity.bean.AuthorityBeanExample;
import com.njtech.smartuniversity.dao.AuthorityBeanMapper;
import com.njtech.smartuniversity.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ritchie on 6/26/18
 */

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityBeanMapper mAuthorityBeanMapper;


    @Override
    public AuthorityBean selectByAuthorityId(int id) {

        return mAuthorityBeanMapper.selectByPrimaryKey(id);
    }

    @Override
    public AuthorityBean update(AuthorityBean authorityBean) {
        int id = authorityBean.getId();
        mAuthorityBeanMapper.updateByPrimaryKey(authorityBean);
        return mAuthorityBeanMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AuthorityBean> getAllAuthorities() {
        AuthorityBeanExample authorityBeanExample = new AuthorityBeanExample();
        AuthorityBeanExample.Criteria criteria = authorityBeanExample.createCriteria();
        criteria.andIdIsNotNull();
        return mAuthorityBeanMapper.selectByExample(authorityBeanExample);
    }

    @Override
    public PageInfo<AuthorityBean> getAuthorityByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AuthorityBean> list = getAllAuthorities();
        return new PageInfo<>(list);
    }

    @Override
    public boolean deleteById(int id) {
        int result = mAuthorityBeanMapper.deleteByPrimaryKey(id);
        return result != 0;
    }

    @Override
    public AuthorityBean addAuthority(AuthorityBean authorityBean) {
        mAuthorityBeanMapper.insert(authorityBean);
        AuthorityBeanExample example = new AuthorityBeanExample();
        AuthorityBeanExample.Criteria criteria = example.createCriteria();
        criteria.andAuNameEqualTo(authorityBean.getAuName());
        List<AuthorityBean> authorityBeanList = mAuthorityBeanMapper.selectByExample(example);
        if (authorityBeanList != null && authorityBeanList.size() > 0) {
            authorityBean = authorityBeanList.get(0);
            return authorityBean;
//            return mAuthorityBeanMapper.selectByPrimaryKey(authorityBean.getId());
        }
        return null;
    }
}

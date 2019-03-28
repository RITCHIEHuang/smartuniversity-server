package com.njtech.smartuniversity.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njtech.smartuniversity.bean.AuthTokenBean;
import com.njtech.smartuniversity.bean.LoginBean;
import com.njtech.smartuniversity.bean.UserBean;
import com.njtech.smartuniversity.bean.UserBeanExample;
import com.njtech.smartuniversity.config.security.JwtTokenUtil;
import com.njtech.smartuniversity.config.security.JwtUserDetailServiceImpl;
import com.njtech.smartuniversity.dao.UserBeanMapper;
import com.njtech.smartuniversity.service.UserService;
import com.njtech.smartuniversity.util.JsonResult;
import com.njtech.smartuniversity.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ritchie on 6/25/18
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserBeanMapper mUserBeanMapper;

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserDetailsService userDetailsService;


    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, @Qualifier("jwtUserDetailServiceImpl") UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public UserBean selectByUserId(String id) {
        UserBeanExample userBeanExample = new UserBeanExample();
        UserBeanExample.Criteria criteria = userBeanExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<UserBean> userBeanList = mUserBeanMapper.selectByExample(userBeanExample);
        if (userBeanList.size() > 0)
            return userBeanList.get(0);
        return null;
    }

    @Override
    public UserBean selectByIdAndPassword(String id, String password) {
        UserBeanExample userBeanExample = new UserBeanExample();
        UserBeanExample.Criteria criteria = userBeanExample.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andPasswordEqualTo(password);
        List<UserBean> userBeanList = mUserBeanMapper.selectByExample(userBeanExample);
        if (userBeanList.size() > 0)
            return userBeanList.get(0);
        return null;
    }

    @Override
    public LoginBean loginByEmailAndPassword(String email, String password) {
        UserBeanExample userBeanExample = new UserBeanExample();
        UserBeanExample.Criteria criteria = userBeanExample.createCriteria();
        criteria.andEmailEqualTo(email);
        List<UserBean> userBeanList = mUserBeanMapper.selectByExample(userBeanExample);
        if (userBeanList.size() > 0) {
            String id = userBeanList.get(0).getId();
            return login(id, password);

        }
        return null;

    }

    @Override
    public LoginBean loginByAccountAndPassword(String account, String password) {
        UserBeanExample userBeanExample = new UserBeanExample();
        UserBeanExample.Criteria criteria = userBeanExample.createCriteria();
        criteria.andAccountEqualTo(account);
        List<UserBean> userBeanList = mUserBeanMapper.selectByExample(userBeanExample);
        if (userBeanList.size() > 0) {
            String id = userBeanList.get(0).getId();
            return login(id, password);
        }
        return null;

    }


    @Override
    public UserBean update(UserBean userBean) {
        String uuid = userBean.getUuid();
        mUserBeanMapper.updateByPrimaryKey(userBean);
        return mUserBeanMapper.selectByPrimaryKey(uuid);
    }

    @Override
    public List<UserBean> getAllUsers() {
        UserBeanExample userBeanExample = new UserBeanExample();
        UserBeanExample.Criteria criteria = userBeanExample.createCriteria();
        criteria.andUuidIsNotNull();
        return mUserBeanMapper.selectByExample(userBeanExample);
    }

    @Override
    public PageInfo<UserBean> getUsersByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserBean> list = getAllUsers();//这个查询会分页
        return new PageInfo<>(list);
    }

    @Override
    public boolean deleteById(String id) {
        UserBeanExample userBeanExample = new UserBeanExample();
        UserBeanExample.Criteria criteria = userBeanExample.createCriteria();
        criteria.andIdEqualTo(id);
        int result = mUserBeanMapper.deleteByExample(userBeanExample);
        return result != 0;
    }

    @Override
    public UserBean addUser(UserBean userBean) {
        return getUserBean(userBean);
    }

    @Override
    public LoginBean login(String id, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(id, password);
        try {
            final Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetails userDetails = userDetailsService.loadUserByUsername(id);


            UserBeanExample userBeanExample = new UserBeanExample();
            UserBeanExample.Criteria userBeanExampleCriteria = userBeanExample.createCriteria();
            userBeanExampleCriteria.andIdEqualTo(id);
            List<UserBean> userBeans = mUserBeanMapper.selectByExample(userBeanExample);
            LoginBean loginBean = new LoginBean();

            loginBean.setUserUUId(userBeans.get(0).getUuid());
            loginBean.setUserID(userBeans.get(0).getId());
            loginBean.setUserType((userBeans.get(0).getAuthId()));
            loginBean.setToken(jwtTokenUtil.generateToken(userDetails));
            return loginBean;
        } catch (BadCredentialsException e) {
            return null;
        }
    }

    @Override
    public UserBean register(UserBean userBean) {
        return getUserBean(userBean);
    }

    private UserBean getUserBean(UserBean userBean) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = userBean.getPassword();
        password = encoder.encode(password);
        userBean.setPassword(password);
        mUserBeanMapper.insert(userBean);
        return selectByUserId(userBean.getId());
    }
}

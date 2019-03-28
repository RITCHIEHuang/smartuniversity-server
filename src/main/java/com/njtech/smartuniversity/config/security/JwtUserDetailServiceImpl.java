package com.njtech.smartuniversity.config.security;

import com.njtech.smartuniversity.bean.AuthorityBean;
import com.njtech.smartuniversity.bean.UserBean;
import com.njtech.smartuniversity.bean.UserBeanExample;
import com.njtech.smartuniversity.dao.UserBeanMapper;
import com.njtech.smartuniversity.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ritchie on 6/25/18
 */
@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserBeanMapper mUserBeanMapper;

    @Autowired
    AuthorityService mAuthorityService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserBeanExample userBeanExample = new UserBeanExample();
        UserBeanExample.Criteria userBeanExampleCriteria = userBeanExample.createCriteria();
        userBeanExampleCriteria.andIdEqualTo(s);
        List<UserBean> userBeans = mUserBeanMapper.selectByExample(userBeanExample);

        if (userBeans == null || userBeans.size() <= 0) {
            throw new UsernameNotFoundException(String.format("没有该用户 '%s'.", s));
        }

        UserBean userBean = userBeans.get(0);
        return create(userBean);
    }


    private JwtUser create(UserBean user) {
        int auth_id = user.getAuthId();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        AuthorityBean authorityBean = mAuthorityService.selectByAuthorityId(auth_id);
        authorities.add(new SimpleGrantedAuthority(authorityBean.getAuName()));
        JwtUser jwtUser = new JwtUser(user);
        jwtUser.setAuthorities(authorities);
        return jwtUser;
    }

}

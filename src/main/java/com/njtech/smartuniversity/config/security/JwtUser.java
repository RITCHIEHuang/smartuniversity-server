package com.njtech.smartuniversity.config.security;

import com.njtech.smartuniversity.bean.UserBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUser extends UserBean implements UserDetails {

    //角色集合
    private Collection<? extends GrantedAuthority> authorities;

    JwtUser(UserBean userBean) {
        setAuthId(userBean.getAuthId());
        setEmail(userBean.getEmail());
        setId(userBean.getId());
        setPassword(userBean.getPassword());
        setPhone(userBean.getPhone());
        setState(userBean.getState());
        setLastPasswordResetTime(userBean.getLastPasswordResetTime());
        setShopType(userBean.getShopType());
        setUuid(userBean.getUuid());
        setType(userBean.getType());
        setAccount(userBean.getAccount());
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
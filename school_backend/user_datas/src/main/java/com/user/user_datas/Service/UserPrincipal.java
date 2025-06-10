package com.user.user_datas.Service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.user.user_datas.Model.user_dao;

public class UserPrincipal implements UserDetails {

    private user_dao userdao;

    public UserPrincipal(user_dao userdao) {
        this.userdao=userdao;
        
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(userdao.getRole()));
    }

    @Override
    public String getPassword() {
        return userdao.getPassword();
        
    }

    @Override
    public String getUsername() {
        return userdao.getUsername();
    }

}

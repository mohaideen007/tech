package com.user.user_datas.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.user.user_datas.Model.user_dao;
import com.user.user_datas.Repo.user_repo;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private user_repo rep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user_dao userdao=rep.findByUsername(username);

        if(userdao==null){

            throw new UsernameNotFoundException("Not Found");
        }
        return new UserPrincipal(userdao);
    }

}

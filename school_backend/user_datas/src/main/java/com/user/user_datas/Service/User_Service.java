package com.user.user_datas.Service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.user_datas.Model.user_dao;
import com.user.user_datas.Repo.user_repo;

@Service
public class User_Service {

    @Autowired
    private user_repo repo;

    @Autowired
    private JwtService jwtserv;

    @Autowired
    private AuthenticationManager authmanag;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public user_dao adduser(user_dao user) {

        user_dao userdao=new user_dao();
        userdao.setUserId(user.getUserId());
        userdao.setUsername(user.getUsername());
        userdao.setRole("USER");
        userdao.setPassword(encoder.encode(user.getPassword()));

        repo.save(userdao);

        return userdao;
    }
    public String login(user_dao user) {
        Authentication authentication = authmanag.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        if (authentication.isAuthenticated()) {
            user_dao dbuser=repo.findByUsername(user.getUsername());
            System.out.println(new Date(System.currentTimeMillis()));
            return jwtserv.generatetoken(dbuser);
        }
        return "not successful";
    }
    public String getpasso(String username) {
        String usernam=encoder.encode(username);
        return usernam;
    }
    }



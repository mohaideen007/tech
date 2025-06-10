package com.user.user_datas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.user_datas.Model.user_dao;
import com.user.user_datas.Service.User_Service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/user")
public class User_controller {

    @Autowired
    private User_Service serv;

    @GetMapping("/")
    public String hello(){
        return "Hii";
    }

    @PostMapping("/adduser")
    public user_dao postMethodName(@RequestBody user_dao user) {
       return serv.adduser(user);
    }

    @PostMapping("/login")
    public String verify(@RequestBody user_dao user) {

        return serv.login(user);
    }

    // @GetMapping("/getpaas/{username}")
    // public String getMethodName(@PathVariable("username") String username) {
    //     return serv.getpasso(username);
    // }
    
    

    

}

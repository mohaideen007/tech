package com.user.user_datas.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.user_datas.Model.user_dao;

public interface user_repo extends JpaRepository<user_dao,Long>{

    
    user_dao findByUsername(String username);

}

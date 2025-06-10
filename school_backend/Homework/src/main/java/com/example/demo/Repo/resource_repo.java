package com.example.demo.Repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.resources_dao;

public interface resource_repo extends JpaRepository<resources_dao,Long>{

    List<resources_dao> findBySubject(String subject);

}

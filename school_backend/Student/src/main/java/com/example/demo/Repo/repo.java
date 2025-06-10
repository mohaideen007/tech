package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.submit;

public interface repo extends JpaRepository<submit,Long>{

    List<submit> findBySubject(String subject);

}

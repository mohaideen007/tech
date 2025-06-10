package com.example.demo.Repo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.home_dao;

public interface work_interf extends JpaRepository<home_dao,Long> {

    // home_dao findByDate(Date date);

List<home_dao> findByIssueddate(LocalDate issueddate); 

}

package com.example.demo.Model;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name="homework")
public class home_dao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long work_id;


    private LocalDate issueddate;
    private String subject;
    private String description;
    private String deadline;
    private String resources;
    

    

}

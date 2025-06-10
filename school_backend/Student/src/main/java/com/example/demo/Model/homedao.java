package com.example.demo.Model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class homedao {


    private Date issueddate;
    private String subject;
    private String description;
    private String deadline;
    private String resources;
    

}

package com.example.demo.Model;

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
@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Table(name="files")
public class resources_dao {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long res_id;

    private String subject;
    private String resources;
    private String description;
    private LocalDate resourcedate;
}

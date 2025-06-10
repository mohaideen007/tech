package com.example.demo.Model;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class dummyresource {


    private String subject;
    private List<String> resources;
    private String description;
    private LocalDate resourcedate;
}



package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Model.home_dao;
import com.example.demo.Model.resources_dao;
import com.example.demo.Model.submitted;
import com.example.demo.Service.work_service;

import jakarta.servlet.http.HttpServletRequest;
import reactor.core.publisher.Flux;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



// title, description, subject, and deadline.
@RestController
@RequestMapping("/homework")
public class work_controller {


    @Autowired
    private work_service serv;

    @GetMapping("/")
    public String getMethodName() {
        return "hello boy";
    }


    @PostMapping("/addhomework")
    @PreAuthorize("hasRole('TEACHER')")
    public home_dao add(@RequestBody home_dao work) {
        return serv.posthomwork(work);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Resource> getMethodName(@PathVariable("id") Long id) throws FileNotFoundException, MalformedURLException {
        return serv.viewreso(id);
    }
    

    @GetMapping("/getwork/{date}")
    public List<home_dao> getMethodName(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate issueddate) {
        return serv.getwork(issueddate);
    }

    @GetMapping("/viewsubject/{subject}")
    public Flux<submitted> visit(@PathVariable("subject") String subject,HttpServletRequest request) {
        return serv.visitwork(subject,request);
    }
   
    @PostMapping("/addresources/{subject}")
     @PreAuthorize("hasRole('TEACHER')")
    public resources_dao postMethodName(@RequestParam("description") String description,@PathVariable("subject") String subject,@RequestParam("file") MultipartFile file) throws IOException {
        
        return serv.addresources(description,subject,file);
    }
    
    @GetMapping("/getresources/{subject}")
    public List<String> getMethodName(@PathVariable("subject") String subject) {
        return serv.getreso(subject);
    }
}

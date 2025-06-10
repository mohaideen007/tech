package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.Model.homedao;
import com.example.demo.Model.submit;
import com.example.demo.Repo.repo;

import jakarta.servlet.http.HttpServletRequest;
import reactor.core.publisher.Flux;

@Service
public class student_service {
    @Autowired
    private repo rep;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public student_service(WebClient.Builder webClientBuilder){
        this.webClientBuilder=webClientBuilder;
    }

    public Flux<homedao> getworky(LocalDate issueddate) {

         Flux<homedao> homed=webClientBuilder.build().get()
        .uri("http://localhost:7000/homework/getwork/"+issueddate)
        .retrieve()
        .bodyToFlux(homedao.class);

        return homed;
       
    }

    public ResponseEntity<submit> submithome(submit sub) {

        rep.save(sub);


        return ResponseEntity.ok()
        .body(sub);
    }

    
    public List<submit> viewname(String subject) {
        return rep.findBySubject(subject);
        
    }

    public Flux<String> askforresources(HttpServletRequest req, String subject) {
String token=req.getHeader("Authorization");
        
        Flux<String> resource=webClientBuilder.build()
        .get()
        .uri("http://localhost:7000/homework/getresources/"+subject)
        .headers(h->h.set("Authorization", token))
        .retrieve()
        .bodyToFlux(String.class);
        return resource;
    }

    
	
	

}

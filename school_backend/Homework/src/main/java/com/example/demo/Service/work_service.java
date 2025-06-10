package com.example.demo.Service;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.Model.home_dao;
import com.example.demo.Model.resources_dao;
import com.example.demo.Model.submitted;
import com.example.demo.Repo.resource_repo;
import com.example.demo.Repo.work_interf;

import jakarta.servlet.http.HttpServletRequest;
import reactor.core.publisher.Flux;

@Service
public class work_service {


    @Autowired
    private work_interf repo;
    @Autowired
    private resource_repo respo;

    @Autowired
    private WebClient.Builder webClientBuilder;


    public work_service(WebClient.Builder webClientBuilder){
        this.webClientBuilder=webClientBuilder;
    }
    
    public home_dao posthomwork(home_dao work) {
        work.setIssueddate(LocalDate.now());
        if (work.getResources()==null) {
            work.setResources("No Resorces");
        }
        repo.save(work);
        return work;
    }
    public ResponseEntity<Resource> viewreso(Long id) throws FileNotFoundException, MalformedURLException {
        home_dao dao=repo.findById(id).orElse(null);
        Path path=Paths.get(dao.getResources());
        Resource resources;
            resources = new UrlResource(path.toUri());
        if(!resources.exists()||!resources.isReadable()){
            throw new FileNotFoundException("NO file exists"+dao.getResources());
        }
        MediaType contenType = MediaTypeFactory.getMediaType(dao.getResources()).orElse(MediaType.APPLICATION_OCTET_STREAM);
        return ResponseEntity.ok()
        .contentType(contenType)
        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+path.getFileName()+"\"")
        .body(resources);
    }

    public List<home_dao> getwork(LocalDate issueddate) {
        return repo.findByIssueddate(issueddate);
    }

    public Flux<submitted> visitwork(String subject,HttpServletRequest request) {
        String token=request.getHeader("Authorization");

        Flux<submitted> submit=webClientBuilder.build()
        .get().uri("http://localhost:8000/student/work/"+subject)
        .headers(h->h.set("Authorization", token))
        .retrieve()
        .bodyToFlux(submitted.class);

        return submit;
    }



    public resources_dao addresources(String desciption,String subject,MultipartFile file) throws IOException {
        String upload="uploads/";
        String filename=System.currentTimeMillis()+"_"+file.getOriginalFilename();
        Path getp=Paths.get(upload, filename);

        Files.createDirectories(getp.getParent());
        Files.write(getp, file.getBytes());

        resources_dao dao=new resources_dao();
        dao.setDescription(desciption);
        dao.setResourcedate(LocalDate.now());
        dao.setResources(getp.toString());
        dao.setSubject(subject);
        respo.save(dao);
        return dao;
    }



    public List<String> getreso(String subject) {
        List<resources_dao> resdao=respo.findBySubject(subject);

        List<String> resolist=new ArrayList();
        for(resources_dao res:resdao){
            resolist.add(res.getResources());
        }
        return resolist;
       
    }




}

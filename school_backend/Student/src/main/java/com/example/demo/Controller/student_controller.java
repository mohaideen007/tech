package com.example.demo.Controller;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model.homedao;
import com.example.demo.Model.submit;
import com.example.demo.Service.student_service;
import jakarta.servlet.http.HttpServletRequest;
import reactor.core.publisher.Flux;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/student")
public class student_controller {
	
	@Autowired
	private student_service serv;
	
	@GetMapping("/")
	public String getty() {
		return "hello";
	}
	
	@GetMapping("/getwork/{date}")
	public Flux<homedao> get(@PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate issueddate) {
		return serv.getworky(issueddate);
	}

	@PostMapping("/submit")
	public ResponseEntity<submit> subhomework(@RequestBody submit sub) {	
	return serv.submithome(sub);
	}

	@GetMapping("/work/{subject}")
	@PreAuthorize("hasRole('TEACHER')")
	public List<submit> getMethodName(@PathVariable("subject") String subject) {
		return serv.viewname(subject);
	}

	@GetMapping("/viewresources/{subject}")
	public Flux<String> askresources(@PathVariable("subject") String subject,HttpServletRequest req) {
		return serv.askforresources(req,subject);
	}



}
	
	
	
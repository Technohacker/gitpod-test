package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DemoApplication {
	@Autowired
	DemoDAO dao;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public DemoResponse testController(@RequestParam() String userID) {
		DemoResponse resp = new DemoResponse();
		resp.name = this.dao.getNameForUserId(userID);
		return resp;
	}

}

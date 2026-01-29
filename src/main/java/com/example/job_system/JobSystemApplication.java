package com.example.job_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class JobSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobSystemApplication.class, args);
	}

}

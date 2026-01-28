package com.example.job_system.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.job_system.dto.CreateJobRequest;
import com.example.job_system.entity.Job;
import com.example.job_system.service.JobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

   public JobController(JobService jobService){
    this.jobService = jobService;
   }

   @PostMapping
   public Job createJob(@Valid @RequestBody CreateJobRequest request){
    return jobService.createJob(request.getType());
   }

   @GetMapping("/{id}")
   public Job getJob(@PathVariable("id") UUID id){
    return jobService.getJob(id);
   }

   @GetMapping("/all")
   public List<Job> getAllJobs(){
    return jobService.getAllJobs();
   }
}

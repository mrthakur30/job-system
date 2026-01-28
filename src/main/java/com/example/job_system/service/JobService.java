package com.example.job_system.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.job_system.entity.Job;
import com.example.job_system.exception.JobNotFoundException;
import com.example.job_system.repository.JobRepository;

@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository){
          this.jobRepository = jobRepository;
    }

    public Job createJob(){
        Job job = new Job("REPORT","PENDING");
        return jobRepository.save(job);
    }

    public Job getJob(UUID id){
        return jobRepository.findById(id)
        .orElseThrow(() -> new JobNotFoundException("Job not found"));
    }
    public List<Job> getAllJobs(){
        return jobRepository.findAll();
        
    }
}

package com.example.job_system.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.job_system.entity.Job;
import com.example.job_system.enums.JobStatus;
import com.example.job_system.enums.JobType;
import com.example.job_system.exception.JobNotFoundException;
import com.example.job_system.repository.JobRepository;

@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository){
          this.jobRepository = jobRepository;
    }

    public Job createJob(String type){
        JobType jobType = JobType.valueOf(type);
        Job job = new Job(jobType,JobStatus.PENDING);
        return jobRepository.save(job);
    }

    public Job getJob(UUID id){
        return jobRepository.findById(id)
        .orElseThrow(() -> new JobNotFoundException("Job not found"));
    }
    public Page<Job> getJobs(Pageable pageable) {
         return jobRepository.findAll(pageable);
    }
}

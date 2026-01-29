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
import com.example.job_system.worker.JobProcessor;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final JobProcessor jobProcessor;

    public JobService(JobRepository jobRepository,
                      JobProcessor jobProcessor) {
        this.jobRepository = jobRepository;
        this.jobProcessor = jobProcessor;
    }

    public Job createJob(String type){
        JobType jobType = JobType.valueOf(type);
        Job job = new Job(jobType,JobStatus.PENDING);

        Job savedJob = jobRepository.save(job);
        jobProcessor.processJob(savedJob.getId());

        return savedJob; 

        
    }

    public Job getJob(UUID id){
        return jobRepository.findById(id)
        .orElseThrow(() -> new JobNotFoundException("Job not found"));
    }
    public Page<Job> getJobs(Pageable pageable, JobStatus status, JobType type) {
           if (status != null && type != null) {
        return jobRepository.findByStatusAndType(status, type, pageable);
    }
    if (status != null) {
        return jobRepository.findByStatus(status, pageable);
    }
    if (type != null) {
        return jobRepository.findByType(type, pageable);
    }
    return jobRepository.findAll(pageable);
    }
}

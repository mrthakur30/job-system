package com.example.job_system.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.job_system.entity.Job;

public interface JobRepository extends JpaRepository<Job,UUID>{
    
}

package com.example.job_system.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.job_system.entity.Job;
import com.example.job_system.enums.JobStatus;
import com.example.job_system.enums.JobType;

public interface JobRepository extends JpaRepository<Job,UUID>{
    Page<Job> findByStatus(JobStatus status, Pageable pageable);

    Page<Job> findByType(JobType type, Pageable pageable);

    Page<Job> findByStatusAndType(
            JobStatus status,
            JobType type,
            Pageable pageable
    );
}

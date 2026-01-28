package com.example.job_system.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.job_system.enums.JobStatus;
import com.example.job_system.enums.JobType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private JobType type;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    private LocalDateTime createdAt;

    protected Job() {
        // JPA only
    }

    public Job(JobType type, JobStatus status) {
        this.type = type;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

     public UUID getId() {
        return id;
    }

    public JobType getType() {
        return type;
    }

    public JobStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}

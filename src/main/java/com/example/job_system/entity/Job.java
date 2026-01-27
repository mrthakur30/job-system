package com.example.job_system.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue
    private UUID id;

    private String type;
    private String status;
    private LocalDateTime createdAt;

    protected Job() {
        // JPA only
    }

    public Job(String type, String status){
        this.type = type;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

     public UUID getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}

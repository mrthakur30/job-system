package com.example.job_system.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateJobRequest {

    @NotBlank(message = "Job type is required")
    public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

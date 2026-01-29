package com.example.job_system.worker;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.job_system.enums.JobStatus;
import com.example.job_system.repository.JobRepository;

@Component
public class JobProcessor {

    private final JobRepository jobRepository;

    private static final int MAX_RETRIES = 3;

    private static final Logger log = LoggerFactory.getLogger(JobProcessor.class);

    public JobProcessor(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Async("jobTaskExecutor")
    public void processJob(UUID jobId) {
        MDC.put("jobId", jobId.toString());
        long start = System.currentTimeMillis();

        try {
            jobRepository.findById(jobId).ifPresent(job -> {
                try {
                    log.info("Job processing started");

                    job.setStatus(JobStatus.PROCESSING);
                    jobRepository.save(job);

                    Thread.sleep(3000); // simulate work

                    if (Math.random() < 0.5) {
                        throw new RuntimeException("Transient failure");
                    }

                    job.setStatus(JobStatus.DONE);
                    jobRepository.save(job);

                    long duration = System.currentTimeMillis() - start;
                    log.info("Job completed successfully in {} ms", duration);

                } catch (InterruptedException | RuntimeException e) {
                    int attempts = job.getRetryCount() + 1;
                    job.setRetryCount(attempts);

                    log.warn("Job failed on attempt {}", attempts, e);

                    if (attempts < MAX_RETRIES) {
                        jobRepository.save(job);
                        processJob(jobId);
                    } else {
                        job.setStatus(JobStatus.FAILED);
                        jobRepository.save(job);
                        log.error("Job failed after max retries");
                    }
                }
            });
        } finally {
            MDC.clear(); 
        }
    }
}

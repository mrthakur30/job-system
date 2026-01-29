package com.example.job_system.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {

    @Bean(name = "jobTaskExecutor")
    public Executor jobTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(4);     // baseline workers
        executor.setMaxPoolSize(8);      // max burst
        executor.setQueueCapacity(50);   // waiting jobs
        executor.setThreadNamePrefix("job-worker-");

        executor.initialize();
        return executor;
    }
}

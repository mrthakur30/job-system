package com.example.job_system;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.job_system.entity.Job;
import com.example.job_system.enums.JobStatus;
import com.example.job_system.enums.JobType;
import com.example.job_system.repository.JobRepository;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class JobIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres
            = new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);

        // 🔴 THIS IS CRITICAL
        registry.add("spring.jpa.database-platform",
                () -> "org.hibernate.dialect.PostgreSQLDialect");
    }

    @Autowired
    JobRepository jobRepository;

    @Test
    void shouldPersistJobInDatabase() {
        Job job = new Job(JobType.REPORT, JobStatus.PENDING);

        Job saved = jobRepository.save(job);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getStatus()).isEqualTo(JobStatus.PENDING);
        assertThat(jobRepository.findAll()).hasSize(1);
    }
}

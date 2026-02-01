Job System – Spring Boot Backend

A production-style backend system to create, track, and process asynchronous jobs with persistence, retries, pagination, and integration testing.

--------------------------------------------------

FEATURES
- Create and manage background jobs
- Job status lifecycle (PENDING, RUNNING, COMPLETED, FAILED)
- Retry mechanism with retry count tracking
- Pagination and sorting for job listing APIs
- Global exception handling
- Structured logging for observability
- Dockerized PostgreSQL setup
- Real integration tests using Testcontainers

--------------------------------------------------

TECH STACK
- Language: Java 17
- Framework: Spring Boot
- Persistence: Spring Data JPA (Hibernate)
- Database: PostgreSQL
- Testing: JUnit 5, Testcontainers
- DevOps: Docker, Docker Compose

--------------------------------------------------

API OVERVIEW
- POST /jobs        -> Create a new job
- GET /jobs         -> List jobs (pagination + sorting)
- GET /jobs/{id}    -> Get job by ID

--------------------------------------------------

CONFIGURATION (example)
spring.datasource.url=jdbc:postgresql://localhost:5432/jobs
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update

--------------------------------------------------

TESTING
Integration tests run against a real PostgreSQL database using Testcontainers.
- No mocks
- Real schema
- Real persistence

Command:
mvn test

--------------------------------------------------

RUN WITH DOCKER
docker-compose up --build

--------------------------------------------------

WHY THIS PROJECT
This project demonstrates:
- Production-grade backend architecture
- Clean layered design (Controller, Service, Repository)
- Reliable database integration testing
- Error handling and observability best practices

--------------------------------------------------

LICENSE
MIT

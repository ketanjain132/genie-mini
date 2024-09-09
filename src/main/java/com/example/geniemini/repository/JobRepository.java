package com.example.geniemini.repository;

import com.example.geniemini.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Details - The Job Repository will provide methods to perform database operations like
//          saving a job or retrieving a job by its ID.
//        - We need a way to interact with our database to store and retrieve job information.
//          Spring Data JPA's JpaRepository provides built-in methods for these operations
//          without writing SQL queries.

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
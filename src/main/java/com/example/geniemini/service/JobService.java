package com.example.geniemini.service;

import com.example.geniemini.model.Job;
import java.util.List;
import java.util.Optional;

//Details - We'll create a service class to handle the business logic of job management,
//          such as submitting jobs and retrieving job details.
//        - The service layer acts as an intermediary between the controller and repository
//          layers. It allows us to implement additional logic (e.g., validations, transformations)
//          that the controller should not handle.


public interface JobService {
    Job submitJob(Job job);
    Optional<Job> getJobById(Long id);
    List<Job> getAllJobs();
    void updateJobStatus(Long id, String status);
}
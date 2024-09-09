package com.example.geniemini.service;

import com.example.geniemini.model.Job;
import com.example.geniemini.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Details - We'll create a service class to handle the business logic of job management,
//          such as submitting jobs and retrieving job details.
//        - The service layer acts as an intermediary between the controller and repository
//          layers. It allows us to implement additional logic (e.g., validations, transformations)
//          that the controller should not handle.


@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job submitJob(Job job) {
        job.setStatus("Submitted");
        return jobRepository.save(job);
    }

    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}

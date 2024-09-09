package com.example.geniemini.controller;

import com.example.geniemini.model.Job;
import com.example.geniemini.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


//Details - We'll create a REST controller to expose HTTP endpoints for:
//              ->   Submitting a new job.
//              ->   Checking the status of an existing job.
//        - The REST controller is the entry point for clients (e.g., UI, scripts)
//          to interact with our backend service. It handles incoming HTTP requests
//          and directs them to the appropriate service methods.

@RestController
@RequestMapping("/jobs") // Base URL for all job-related endpoints
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    // Submit a new job
    @PostMapping("/submit")
    public Job submitJob(@RequestBody Job job) {
        job.setStatus("Submitted");
        return jobRepository.save(job); // Save and return the job
    }

    // Get a job by ID
    @GetMapping("/{id}")
    public Optional<Job> getJobById(@PathVariable Long id) {
        return jobRepository.findById(id); // Retrieve and return the job if it exists
    }

    // Get all jobs
    @GetMapping("/")
    public List<Job> getAllJobs() {
        return jobRepository.findAll(); // Retrieve and return all jobs
    }
}

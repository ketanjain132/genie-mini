package com.example.geniemini.controller;

import com.example.geniemini.model.Job;
import com.example.geniemini.service.JobService;
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
    private JobService jobService;

    @PostMapping("/submit")
    public Job submitJob(@RequestBody Job job) {
        return jobService.submitJob(job);
    }

    @GetMapping("/{id}")
    public Optional<Job> getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    @GetMapping("/")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }
}

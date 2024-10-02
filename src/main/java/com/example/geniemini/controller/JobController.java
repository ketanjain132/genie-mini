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
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // Submit a new job
    @PostMapping("/submit")
    public Job submitJob(@RequestBody Job job) {
        return jobService.submitJob(job);
    }

    // Get a job by ID
    @GetMapping("/{id}")
    public Optional<Job> getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    // Get all jobs
    @GetMapping("/")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    // Update job status (for internal use)
    @PutMapping("/{id}/status")
    public void updateJobStatus(@PathVariable Long id, @RequestParam String status) {
        jobService.updateJobStatus(id, status);
    }
}
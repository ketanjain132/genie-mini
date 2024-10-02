package com.example.geniemini.service;

import com.example.geniemini.model.Job;
import com.example.geniemini.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Job submitJob(Job job) {
        job.setStatus("SUBMITTED");
        job.setSubmittedAt(LocalDateTime.now());
        return jobRepository.save(job);
    }

    @Override
    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public void updateJobStatus(Long id, String status) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            job.setStatus(status);
            if (status.equals("RUNNING")) {
                job.setStartedAt(LocalDateTime.now());
            } else if (status.equals("COMPLETED") || status.equals("FAILED")) {
                job.setCompletedAt(LocalDateTime.now());
            }
            jobRepository.save(job);
        }
    }
}

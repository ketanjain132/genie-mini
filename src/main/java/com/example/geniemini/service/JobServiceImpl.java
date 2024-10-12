package com.example.geniemini.service;

import com.example.geniemini.model.Job;
import com.example.geniemini.repository.JobRepository;
import com.example.geniemini.util.CommandExecutor;
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
        Job savedJob = jobRepository.save(job);

        // Submit the job to Hadoop/Spark
        new Thread(() -> executeJob(savedJob)).start();

        return savedJob;
    }

    private void executeJob(Job job) {
        try {
            // Update job status to RUNNING
            updateJobStatus(job.getId(), "RUNNING");

            // Example Hadoop MapReduce job command
            String hadoopCommand = String.format("hadoop jar %s/share/hadoop/mapreduce/hadoop-mapreduce-examples-*.jar wordcount %s %s/output_%d",
                    System.getenv("HADOOP_HOME"),
                    job.getInputData(),
                    System.getenv("HADOOP_HOME"),
                    job.getId());

            String output = CommandExecutor.executeCommand(hadoopCommand);
            System.out.println("Hadoop Job Output: " + output);

            // Update job status to COMPLETED
            updateJobStatus(job.getId(), "COMPLETED");
        } catch (Exception e) {
            e.printStackTrace();
            // Update job status to FAILED in case of exception
            updateJobStatus(job.getId(), "FAILED");
        }
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
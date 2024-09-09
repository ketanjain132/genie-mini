package com.example.geniemini.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Marks this class as an entity, meaning it will be mapped to a database table
public class Job {

    @Id // This field is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates a unique ID
    private Long id;

    private String jobType; // To store the type of job (e.g., Spark, Hadoop)
    private String inputData; // To store input data information (e.g., data path)
    private String status; // To track the status of the job (e.g., Submitted, Running, Completed)

    // Getters and Setters (to access and modify private fields)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

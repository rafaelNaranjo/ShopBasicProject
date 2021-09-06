package com.rafael.batch.jobs.services;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServicesProducts {

    @Autowired
    JobLauncher jobProductsLauncher;
    @Autowired
    Job proccessorProductsJob;

    public Long executeProductsJob()throws Exception{
        JobExecution jobExecution = jobProductsLauncher
                .run(proccessorProductsJob,
                        new JobParametersBuilder()
                        .addLong("idInicio",System.nanoTime()).toJobParameters()
                );
        return jobExecution.getJobId();
    }
}

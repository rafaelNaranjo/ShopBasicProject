package com.rafael.batch.jobs.services;

import javafx.beans.binding.When;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class JobServicesProductsTest {

    @InjectMocks
    private JobServicesProducts jobServicesProducts;

    @Mock
    private JobLauncher jobProductsLauncher;

    @Mock
    private Job proccessorProductsJob;

    @Mock
    private JobExecution jobExecution;

    @Test
    public void jobServicesProductsTest()throws Exception{
        when(jobProductsLauncher.run(any(),any())).thenReturn(new JobExecution(new JobInstance(1l,"any"),new JobParameters()));
        Long id = jobServicesProducts.executeProductsJob();
        assertEquals(1l,id);
    }

}
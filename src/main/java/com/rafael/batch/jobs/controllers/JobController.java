package com.rafael.batch.jobs.controllers;

import com.rafael.batch.jobs.services.JobServicesProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobServicesProducts jobServicesProducts;

    @PostMapping("/execute")
    public ResponseEntity<Long> job() throws Exception{
        Long idJob = jobServicesProducts.executeProductsJob();
        return ResponseEntity.ok().body(idJob);
    }
}

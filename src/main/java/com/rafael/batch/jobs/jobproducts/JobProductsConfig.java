package com.rafael.batch.jobs.jobproducts;

import com.rafael.batch.jobs.models.ProductDTO;
import com.rafael.batch.jobs.utils.JobProductsListener;
import com.rafael.batch.products.model.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class JobProductsConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    JobRepository jobRepository;

    @Value("${file.input}")
    private String fileInput;

    @Bean
    public JobLauncher jobProductsLauncher()throws Exception{
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }

    @Bean
    public Job proccessorProductsJob(JobProductsListener listener, Step stepProducts){
        return jobBuilderFactory.get("proccessorProductsJob")
                .listener(listener)
                .flow(stepProducts)
                .end()
                .build();
    }

    @Bean
    public Step stepProducts(){
        return stepBuilderFactory.get("stepProducts")
                .<ProductDTO, Product> chunk(1000)
                .reader(reader())
                .processor(proccessor())
                .writer(writer())
                .build();
    }

    @Bean
    public FlatFileItemReader<ProductDTO> reader(){
        FlatFileItemReader<ProductDTO> readerNew = new FlatFileItemReaderBuilder<ProductDTO>()
                .name("productProccessor")
                .resource(new ClassPathResource(fileInput))
                .delimited()
                .names("nombre","marca","precio","cantidad","estado","descuento")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<ProductDTO>(){{
                    setTargetType(ProductDTO.class);
                }})
                .build();
        readerNew.setLinesToSkip(1);
        return readerNew;
    }
    @Bean
    public ProductProccessor proccessor(){
        return new ProductProccessor();
    }
    @Bean
    public ProductWriter writer(){
        return new ProductWriter();
    }
}

package com.example.quartz.configuration;

import com.example.quartz.job.sample.SampleJob;
import com.example.quartz.job.sample.SampleJobDetail;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mtumilowicz on 2018-07-22.
 */
@Configuration
public class JobProducer {
    @Bean
    @SampleJobDetail
    public JobDetail sampleJobDetail() {
        return JobBuilder
                .newJob(SampleJob.class)
                .storeDurably()
                .build();
    }
}

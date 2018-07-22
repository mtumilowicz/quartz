package com.example.quartz;

import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }

    @Bean
    public Trigger sampleJobTrigger(JobDetail jobDetail) {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(2)
                .repeatForever();

        return TriggerBuilder
                .newTrigger()
                .forJob(jobDetail)
                .withSchedule(scheduleBuilder)
                .build();
    }
}

package com.example.quartz.job.sample;

import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by mtumilowicz on 2018-07-22.
 */
public class SampleJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) {
        System.out.println("SampleJob invoked!");
    }

}

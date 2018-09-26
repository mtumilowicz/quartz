# quartz
The main goal of this project is to explore basic features of `Quartz` - 
job scheduler.

# preface
**Job scheduler** is a service component that is responsible for executing 
(or notifying) other software components when a pre-determined (scheduled) 
time arrives.

# definition
Quartz is a job scheduling library that can be integrated with, or used 
along side virtually any other software system.

Quartz is very light-weight, and requires very little setup/configuration.

# pros
* open source,
* support for JTA: Quartz can manage JTA transactions (begin and commit 
them) around the execution of a Job, so that the work performed by the 
Job automatically happens within a JTA transaction,
* support for clustering (useful for load balancing),
* `JDBCJobStore` - all Jobs and Triggers configured as “non-volatile” are 
stored in a relational database via JDBC,
* `RAMJobStore` - ll Jobs and Triggers are stored in RAM and therefore 
do not persist between program executions - but this has the advantage 
of not requiring an external database.

# manual
* `pom.xml`
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-quartz</artifactId>
    </dependency>
    ```

* quartz job (`SampleJob.java`)
    1. Inherit `QuartzJobBean`
        ```
        SampleJob extends QuartzJobBean
        ```
    2. Implement `executeInternal(JobExecutionContext context)`
        ```
        @Override
        protected void executeInternal(JobExecutionContext context) {
            System.out.println("SampleJob invoked!");
        }
        ```
* produce job as a `JobDetail` (`JobProducer.java`)
    ```
    @Bean
    @SampleJobDetail
    public JobDetail sampleJobDetail() {
        return JobBuilder
                .newJob(SampleJob.class)
                .storeDurably()
                .build();
    }
    ```
    
* bind trigger to the job detail (`TriggerProducer.java`)
    ```
    @Bean
    public Trigger sampleJobTrigger(@SampleJobDetail JobDetail jobDetail) {
        SimpleScheduleBuilder scheduleBuilder = ...
    
        return TriggerBuilder
                .newTrigger()
                .forJob(jobDetail)
                .withSchedule(scheduleBuilder)
                .build();
    }
    ```
    where `scheduleBuilder` is for example:
    ```
    SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                    .simpleSchedule()
                    .withIntervalInSeconds(2)
                    .repeatForever();
    ```
    

    

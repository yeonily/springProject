package com.codefarm.codefarmer.config;

import com.codefarm.codefarmer.quartz.JobA;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;


/*
* Job의 역할은 일정 시간마다 주기적으로 실행해주는 주체
* */


@Configuration
public class JobConfig {
    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void start(){
        JobDetail jobDetail = buildJobDetail(JobA.class, new HashMap());

        try{
            scheduler.scheduleJob(jobDetail, buildJobTrigger("0/5 * * * * ?")); // 5초 간격으로 실행
        } catch(SchedulerException e){
            e.printStackTrace();
        }
    }

    public Trigger buildJobTrigger(String scheduleExp){
        return TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
    }

    public JobDetail buildJobDetail(Class job, Map params){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(params);

        return newJob(job).usingJobData(jobDataMap).build();
    }
}

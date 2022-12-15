package com.codefarm.codefarmer.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class JobA extends QuartzJobBean {

    private static final Logger log = LoggerFactory.getLogger(JobA.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        log.info("여기에 반복 실행될 메소드를 추가해야함");
    }
}

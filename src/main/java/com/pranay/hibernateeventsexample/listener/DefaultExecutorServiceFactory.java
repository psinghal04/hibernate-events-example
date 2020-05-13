package com.pranay.hibernateeventsexample.listener;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("threads")
public class DefaultExecutorServiceFactory implements ExecutorServiceFactory {

    private int threadPoolSize;

    private ExecutorService service;

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public ExecutorService getExecutorService() {
        if (this.service == null) {
            this.service = Executors.newFixedThreadPool(threadPoolSize);
        }

        return this.service;
    }
}

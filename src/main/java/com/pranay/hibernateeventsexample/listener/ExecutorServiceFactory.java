package com.pranay.hibernateeventsexample.listener;

import java.util.concurrent.ExecutorService;

public interface ExecutorServiceFactory {
    ExecutorService getExecutorService();
}

package com.mc.resourcesshareback.config;

import java.util.concurrent.ExecutorService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.mc.resourcesshareback.handler.TaskRejectedHandler;

@Configuration
public class ThreadPoolConfig {
    @Bean
    public ExecutorService taskExecutor(TaskRejectedHandler handler) {
        return new ThreadPoolExecutor(
            2, // corePoolSize
            5, // maxPoolSize - 增加最大线程数
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100), // 队列容量改大
            Executors.defaultThreadFactory(),
            handler // 自定义拒绝策略
        );
    }
}


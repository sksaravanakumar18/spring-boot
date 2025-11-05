package com.springboot.master.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Application Configuration
 * 
 * Demonstrates:
 * - General application configuration
 * - Cache configuration
 * - Async configuration
 * - Thread pool configuration
 */
@Configuration
@EnableAsync
public class AppConfig {

    /**
     * Cache Manager configuration
     * Demonstrates: Cache configuration for development
     */
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("users", "posts");
    }

    /**
     * Async Task Executor configuration
     * Demonstrates: Thread pool configuration for async operations
     */
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("async-");
        executor.initialize();
        return executor;
    }
}

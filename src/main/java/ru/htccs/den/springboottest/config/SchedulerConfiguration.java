package ru.htccs.den.springboottest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

/**
 * Шедулер для асинхронного чтения новостей из БД на h2
 */
@Configuration
public class SchedulerConfiguration {
    private final Integer connectionPoolSize;

    /**
     * Конструктор объекта
     * @param connectionPoolSize размер пула
     */
    public SchedulerConfiguration(@Value("${spring.datasource.maximum-pool-size}") Integer connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }

    @Bean
    public Scheduler jdbcScheduler() {
        return Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize));
    }
}

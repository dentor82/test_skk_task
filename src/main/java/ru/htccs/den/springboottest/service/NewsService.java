package ru.htccs.den.springboottest.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import ru.htccs.den.springboottest.models.News;
import ru.htccs.den.springboottest.repository.INewsRepository;

import java.util.concurrent.Callable;

/**
 * Сервис загрузки новостей
 */
@Service
public class NewsService {
    private final INewsRepository repository;
    private final Scheduler scheduler;

    /**
     * Конструктор сервиса
     * @param inRepository - репозиторий новостей
     * @param inScheduler - шедулер для обеспечения асинхронного доступа к БД H2
     */
    public NewsService(INewsRepository inRepository, @Qualifier("jdbcScheduler") Scheduler inScheduler) {
        this.repository = inRepository;
        this.scheduler = inScheduler;
    }

    /**
     * Получить список всех новостей из базы
     * @return - реактивный поток доступа к данным
     */
    public Mono<Iterable<News>> findAll() {
        return async(() -> repository.findAll());
    }

    /**
     * Асинхронный вызов
     */
    private <T> Mono<T> async(Callable<T> callable) {
        return Mono.fromCallable(callable).publishOn(scheduler);
    }
}

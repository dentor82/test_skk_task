package ru.htccs.den.springboottest.controllers;

import org.apache.activemq.Message;
import org.apache.activemq.command.MessageId;
import org.springframework.messaging.MessageHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.htccs.den.springboottest.jms.NewsMessage;
import ru.htccs.den.springboottest.messages.MessagingService;
import ru.htccs.den.springboottest.messages.MessagingServiceStub;
import ru.htccs.den.springboottest.models.News;
import ru.htccs.den.springboottest.repository.INewsRepository;
import ru.htccs.den.springboottest.service.NewsService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Контроллер для вывода новостей
 */
@Controller
@RequestMapping(value = "/newsData", method = RequestMethod.GET)
public class NewsController {
    private NewsService newsRepository;

    public NewsController(NewsService inRepository) {
        this.newsRepository = inRepository;
    }

    /**
     * Используем реактивные потоки для вывода ленты новостей на страницу
     */
    @GetMapping
    public Mono<Iterable<News>> findAll(Model model) {
        Mono<Iterable<News>> retValue = newsRepository.findAll();
        model.addAttribute("news", retValue.block());
        return retValue;
    }

    @Scheduled(fixedDelay=10000)
    public void sendRefreshNews() {
        Map<String, Object> mapHeader = new HashMap<>();
        mapHeader.put(MessageHeaders.ID, UUID.randomUUID());
        NewsMessage msg = new NewsMessage(mapHeader);
        // Отправим запрос через брокера
    }
}

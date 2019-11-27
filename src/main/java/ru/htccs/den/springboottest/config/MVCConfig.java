package ru.htccs.den.springboottest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Конфигурирование приложения с помошью сопоставления путей с отображением
 */
@EnableWebMvc
@EnableAsync
@EnableScheduling
@Configuration
public class MVCConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Страница по умолчанию
        registry.addViewController("/").setViewName("welcomePage");
        // Страница приветствия
        registry.addViewController("/welcome").setViewName("welcomePage");
        // Страница с новостями
        registry.addViewController("/news").setViewName("news");
        //registry.addViewController("/login").setViewName("login");
    }
}

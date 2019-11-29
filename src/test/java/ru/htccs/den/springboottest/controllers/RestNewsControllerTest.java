package ru.htccs.den.springboottest.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.htccs.den.springboottest.jms.NewsMessageListener;
import ru.htccs.den.springboottest.models.NewsBody;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestNewsControllerTest {

    @Mock
    private NewsMessageListener newsListener;

    @Mock
    private RestNewsController voucherController;

    @Test
    void saveNews() {
        NewsBody newsB = new NewsBody(1L, new Date(), "Заголовок", "Текст");
        voucherController.saveNews(newsB);
        // Проверим, что функция выполнилась
        Mockito.verify(voucherController, Mockito.atLeastOnce()).saveNews(newsB);
        // Проверим, что функция для обработки выполнилась максимум один раз
        Mockito.verify(newsListener, Mockito.atMost(1)).handleMessage(Mockito.any());
    }
}
package ru.htccs.den.springboottest.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MainControllerTest {

    @InjectMocks
    private MainController wMContr;

    @BeforeEach
    void setUp() {
    }

    @Test
    void welcomePage() {
        ModelAndView retView = wMContr.welcomePage(null);
        // Проверим чтобы в ответе было заполнено отображение
        assertEquals("welcomePage", retView.getViewName());
        // Заполнено поле приветствия
        assertNotNull(retView.getModel().get("messageHellow"));
    }
}
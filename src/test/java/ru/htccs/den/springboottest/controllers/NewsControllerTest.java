package ru.htccs.den.springboottest.controllers;

import org.apache.activemq.command.MessageId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import ru.htccs.den.springboottest.jms.NewsMessage;
import ru.htccs.den.springboottest.messages.MessagingServiceStub;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

class NewsControllerTest {

    private MessagingServiceStub msgService;
    private Message<NewsMessage> respMsg;

    @BeforeEach
    void setUp() {
        msgService = new MessagingServiceStub();
    }

    @Test
    void sendRefreshNews() {
        Map<String, Object> mapHeader = new HashMap<>();
        mapHeader.put(MessageHeaders.ID, UUID.randomUUID());
        NewsMessage msg = new NewsMessage(mapHeader);
        assertNotNull(msg.getHeaders().getId());
        MessageId saveId = msgService.send(msg);
        // Проверим идентификаторы отправленного сообщения
        assertNotEquals(saveId.toString(), msg.getHeaders().getId().toString());

        Throwable thrown = catchThrowable(() -> {
            respMsg = msgService.receive(saveId, NewsMessage.class);
        });
        if ((thrown != null) && (thrown.getCause() instanceof TimeoutException)) {
            // Если таймаут
            assertThat(thrown).isInstanceOf(TimeoutException.class);

        } else {
            // Если нет таймаута
            assertNull(thrown);
        }

        // Заглушка возвращает пусто
        assertNull(respMsg);
    }
}
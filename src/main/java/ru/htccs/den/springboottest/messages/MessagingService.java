package ru.htccs.den.springboottest.messages;

import org.apache.activemq.command.MessageId;
import org.springframework.messaging.Message;

import java.util.concurrent.TimeoutException;

/**
 * Ориентировочный интерфейс нашего messaging решения.
 */
public interface MessagingService {

    /**
     * Отправка сообщения в шину.
     *
     * @param msg сообщение для отправки.
     *
     * @return идентификатор отправленного сообщения (correlationId)
     */
    <T> MessageId send(Message<T> msg);

    /**
     * Встает на ожидание ответа по сообщению с messageId.
     *
     * Редко, но может кинуть исключение по таймауту.
     *
     * @param messageId идентификатор сообщения, на которое ждем ответ.
     * @param messageType тип сообщения, к которому необходимо привести ответ.
     * @return Тело ответа.
     */
    <T> Message<T> receive(MessageId messageId, Class<T> messageType) throws TimeoutException;
}

package ru.htccs.den.springboottest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import ru.htccs.den.springboottest.jms.NewsMessageListener;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;

/**
 * Конфигурируем подключение к брокеру сообщений, для примера
 */
public class ReciverConfig {
    @Value("${broker-url}")
    private String brokerUrl;

    @Value("${reciver.queue}")
    private String nameQueue;

    @Bean
    public ConnectionFactory receiverConnectionFactory() {
        ConnectionFactory connectionFactory = null;
        // Создадим фабрику подключения
        //connectionFactory = new ConnectionFactory();
        // укажем адрес брокера
        //connectionFactory.setBrokerURL(brokerUrl);

        return connectionFactory;
    }

    /**
     * Добавим подключение в контейнер по умолчанию
     */
    @Bean
    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

        factory.setConnectionFactory(receiverConnectionFactory());

        return factory;
    }

    /**
     * Создадим слушателя сообщений и добавим в контейнер
     */
    @Bean
    public DefaultMessageListenerContainer messageListenerContainer() {
        SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
        // Создадим слушателя и подпишем на события
        //endpoint.setMessageListener(new NewsMessageListener());
        endpoint.setDestination(nameQueue);

        return defaultJmsListenerContainerFactory().createListenerContainer(endpoint);
    }
}

package ru.htccs.den.springboottest.messages;

import org.apache.activemq.command.MessageId;
import org.springframework.messaging.Message;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class MessagingServiceStub extends AbstractStub implements MessagingService {

    @Override
    public <T> MessageId send(Message<T> msg) {
        return new MessageId("id:" + new Random().nextInt(1000));
    }

    @Override
    public <T> Message<T> receive(MessageId messageId, Class<T> messageType) throws TimeoutException {
        if(shouldThrowTimeout()) {
            sleep();

            throw new TimeoutException("Timeout!");
        }

        if(shouldSleep()) {
            sleep();
        }

        // return our stub message here.
        return null;
    }
}

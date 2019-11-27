package ru.htccs.den.springboottest.messages;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.htccs.den.springboottest.messages.MessagingService;
import ru.htccs.den.springboottest.messages.SendMailer;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class SendMailerStub extends AbstractStub implements SendMailer {
    private Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void sendMail(EmailAddress toAddress, EmailContent messageBody) throws TimeoutException {
        if(shouldThrowTimeout()) {
            sleep();

            throw new TimeoutException("Timeout!");
        }

        if(shouldSleep()) {
            sleep();
        }

        // ok.
        log.info("Message sent to {}, body {}.", toAddress, messageBody);
    }
}
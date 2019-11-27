package ru.htccs.den.springboottest.messages;

import java.util.concurrent.TimeoutException;

/**
 * Ориентировочный интерфейс мейлера.
 */
public interface SendMailer {
    void sendMail(EmailAddress toAddress, EmailContent messageBody) throws TimeoutException;
}

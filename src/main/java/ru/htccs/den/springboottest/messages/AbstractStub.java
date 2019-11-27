package ru.htccs.den.springboottest.messages;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AbstractStub {
    @SneakyThrows
    protected static void sleep() {
        Thread.sleep(TimeUnit.MINUTES.toMillis(1));
    }

    protected static boolean shouldSleep() {
        return new Random().nextInt(10) == 1;
    }

    protected static boolean shouldThrowTimeout() {
        return new Random().nextInt(10) == 1;
    }
}

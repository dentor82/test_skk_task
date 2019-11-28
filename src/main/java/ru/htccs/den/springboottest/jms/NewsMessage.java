package ru.htccs.den.springboottest.jms;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import ru.htccs.den.springboottest.models.News;

import java.util.*;

public class NewsMessage implements Message<List<News>> {

    private MessageHeaders header;
    private List<News> list;

    public NewsMessage(Map<String, Object> inMap) {
        this.header = new MessageHeaders(inMap);
        this.list = new ArrayList<News>();
    }

    @Override
    public List<News> getPayload() {
        return this.list;
    }

    @Override
    public MessageHeaders getHeaders() {
        return this.header;
    }
}

package ru.htccs.den.springboottest.jms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import ru.htccs.den.springboottest.messages.MessageListener;
import ru.htccs.den.springboottest.models.News;
import ru.htccs.den.springboottest.repository.INewsRepository;

public class NewsMessageListener implements MessageListener<NewsMessage> {
    private Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private INewsRepository newsRepository;
    @Override
    public void handleMessage(Message<NewsMessage> incomingMessage) {
        try {
            for (News elem : incomingMessage.getPayload().getPayload()) {
                newsRepository.save(elem);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            for (StackTraceElement elem : e.getStackTrace()) {
                log.debug(elem.toString());
            }
        }
    }
}

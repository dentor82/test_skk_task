package ru.htccs.den.springboottest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.web.bind.annotation.*;
import ru.htccs.den.springboottest.jms.NewsMessage;
import ru.htccs.den.springboottest.jms.NewsMessageListener;
import ru.htccs.den.springboottest.models.News;
import ru.htccs.den.springboottest.models.NewsBody;
import ru.htccs.den.springboottest.settings.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@ResponseBody
public class RestNewsController {

    private NewsMessageListener newsListener;

    public RestNewsController(NewsMessageListener inNewsListener) {
        this.newsListener = inNewsListener;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/saveNews")
    public String saveNews(@RequestBody NewsBody inBody) {
        assert inBody.getId() != null;
        assert inBody.getCreateDate() != null;
        assert inBody.getTitle() != null;
        assert inBody.getText() != null;

        Map<String, Object> mapHeader = new HashMap<>();
        mapHeader.put(MessageHeaders.ID, UUID.randomUUID());
        NewsMessage msg = new NewsMessage(mapHeader);

        News newNews = new News();
        newNews.setId(inBody.getId());

        newNews.setCreateDate(inBody.getCreateDate());
        newNews.setTitle(inBody.getTitle());
        newNews.setText(inBody.getText());
        msg.getPayload().add(newNews);

        Message<NewsMessage> sendMsg = new Message<NewsMessage>() {
            @Override
            public NewsMessage getPayload() {
                return msg;
            }

            @Override
            public MessageHeaders getHeaders() {
                Map<String, Object> mapHeader = new HashMap<>();
                mapHeader.put(MessageHeaders.ID, UUID.randomUUID());
                return new MessageHeaders(mapHeader);
            }
        };
        newsListener.handleMessage(sendMsg);
        return Constants.RESPONSE_OK;
    }
}

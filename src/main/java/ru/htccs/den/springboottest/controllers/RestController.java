package ru.htccs.den.springboottest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.htccs.den.springboottest.jms.NewsMessage;
import ru.htccs.den.springboottest.jms.NewsMessageListener;
import ru.htccs.den.springboottest.models.News;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/api/saveNews")
@ResponseBody
public class RestController {
    @Autowired
    private NewsMessageListener newsListener;
    @GetMapping
    public String saveNews(@RequestParam("id") long id, @RequestParam("date") String inDate, @RequestParam("title") String inTitle, @RequestParam("text") String inText) {
        Map<String, Object> mapHeader = new HashMap<>();
        mapHeader.put(MessageHeaders.ID, UUID.randomUUID());
        NewsMessage msg = new NewsMessage(mapHeader);

        News newNews = new News();
        newNews.setId(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tmpDate = null;
        try {
            tmpDate = sdf.parse(inDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        newNews.setCreateDate(tmpDate);
        newNews.setTitle(inTitle);
        newNews.setText(inText);
        msg.getPayload().add(newNews);

        Message<NewsMessage> sendMsg = new Message<NewsMessage>() {
            @Override
            public NewsMessage getPayload() {
                return msg;
            }

            @Override
            public MessageHeaders getHeaders() {
                return null;
            }
        };
        newsListener.handleMessage(sendMsg);
        return "OK";
    }
}

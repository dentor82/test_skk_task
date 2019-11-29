package ru.htccs.den.springboottest.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.htccs.den.springboottest.settings.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

class NewsBodyTest {
    @Test
    public void deserialize() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.registerModule(new BasketJsonModule());

        NewsBody news = objectMapper.readValue(
                "{\"id\": 1,\"createDate\": \"2009-02-15 00:00:00\",\"title\": \"Заголовок\",\"text\": \"Текст\"}",
                NewsBody.class);

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        assertEquals(1L , news.getId());
        assertEquals(sdf.parse("2009-02-15 00:00:00"), news.getCreateDate());
        assertEquals("Заголовок", news.getTitle());
        assertEquals("Текст", news.getText());
    }
}
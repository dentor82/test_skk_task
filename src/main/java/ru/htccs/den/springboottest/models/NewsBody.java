package ru.htccs.den.springboottest.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.htccs.den.springboottest.settings.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Новость в формате JSON
 */
public class NewsBody {
    private Long id;
    @JsonFormat(pattern = Constants.DATE_FORMAT, timezone = Constants.LOCAL_TIMEZONE)
    private Date createDate;
    private String title;
    private String text;

    public NewsBody() {

    }

    public NewsBody(Long id, Date createDate, String title, String text) {
        this.id = id;
        this.createDate = createDate;
        this.title = title;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}

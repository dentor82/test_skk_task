package ru.htccs.den.springboottest.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Объект новость, аннотации для data-jpa
 */

@Data
@Entity
@Table(name = "list_news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "title_news")
    private String title;

    @Column(name = "text_news")
    private String text;
}

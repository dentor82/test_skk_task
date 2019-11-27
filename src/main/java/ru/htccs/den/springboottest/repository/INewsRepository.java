package ru.htccs.den.springboottest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.htccs.den.springboottest.models.News;

@Repository
public interface INewsRepository extends CrudRepository<News, Long> { }

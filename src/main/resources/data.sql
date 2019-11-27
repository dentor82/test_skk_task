INSERT INTO login_users (id, user_name, user_password) VALUES (1, '1', '{noop}123');

INSERT INTO list_news (id, create_date, title_news, text_news) VALUES
(1, PARSEDATETIME('22.11.2019', 'dd.MM.yyyy'), 'Ужас на улицах', 'Выпал снег'),
(2, PARSEDATETIME('23.11.2019', 'dd.MM.yyyy'), 'Конец цивилизации', 'Мусор бросают прямо мимо урны');

-- PARSEDATETIME('Sat, 3 Feb 2001 03:05:06 GMT', 'EEE, d MMM yyyy HH:mm:ss z', 'en', 'GMT')
package ru.htccs.den.springboottest.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.htccs.den.springboottest.repository.ILoginRepository;

/**
 * Сервис загрузки пользователей
 */
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    ILoginRepository userLoginRepository;

    /**
     * Загрузить по имени пользователя
     * @param username - имя пользователя
     * @return объект для аутентификации
     * @throws UsernameNotFoundException исключение пользователь не найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userLoginRepository.findByUserName(username);
    }
}

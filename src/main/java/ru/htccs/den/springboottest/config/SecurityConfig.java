package ru.htccs.den.springboottest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.htccs.den.springboottest.repository.ILoginRepository;
import ru.htccs.den.springboottest.service.impl.UserDetailsServiceImpl;

/**
 * Конфигурирование приложения с подключением к web security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    // Без аутентификации
                .authorizeRequests().antMatchers("/").permitAll()
                // Все только после аутентификации
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/welcome")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    public UserDetailsService userDetailsService(ILoginRepository inUser) {
        return new UserDetailsServiceImpl(inUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * Настроим универсальное хранение паролей "{тип шифрования} пароль"
     */
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider retValue = new DaoAuthenticationProvider();
        retValue.setUserDetailsService(userDetailsService);
        retValue.setPasswordEncoder(passwordEncoder());
        return retValue;
    }
}

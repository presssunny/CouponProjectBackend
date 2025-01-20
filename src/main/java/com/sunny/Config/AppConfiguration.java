package com.sunny.Config;

import com.sunny.LogIn.ClientSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@EnableScheduling

public class AppConfiguration implements WebMvcConfigurer {

    @Bean
    public Map<String, ClientSession> clientSessionMap() {
        return new HashMap<>();
    }

    @Bean
    public Map<UUID, LocalDateTime> tokensMap() {return new HashMap<>();}
}


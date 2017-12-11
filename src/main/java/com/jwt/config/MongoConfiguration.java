package com.jwt.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfiguration
{
    public @Bean
    MongoClient mongoClient() {
        return new MongoClient("localhost");
    }
}
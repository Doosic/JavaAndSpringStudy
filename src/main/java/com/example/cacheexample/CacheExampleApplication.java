package com.example.cacheexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class CacheExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheExampleApplication.class, args);
    }

}

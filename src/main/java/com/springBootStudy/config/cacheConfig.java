package com.springBootStudy.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@EnableCaching
@Configuration
public class cacheConfig {

//    @Bean
//    public RedisCacheConfiguration redisCacheConfiguration(){
//        return RedisCacheConfiguration.defaultCacheConfig()
//                .computePrefixWith(name -> name + ":")
//                .entryTtl(Duration.ofSeconds(15))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//    }
}

package com.bennzhang.springboot.springbootredisdemo.configuration;

import com.bennzhang.springboot.springbootredisdemo.domain.UserSession;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
    @Bean(name="jedisConnectionFactory" )
    @ConfigurationProperties(prefix = "spring.redis")
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean(name="redisTemplate")
    RedisTemplate<String, UserSession> redisTemplate() {
        RedisTemplate<String, UserSession> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    @Bean(name="redisCacheManager")
    RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
        return redisCacheManager;
    }
}

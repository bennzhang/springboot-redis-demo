package com.bennzhang.springboot.springbootredisdemo.repository;

import com.bennzhang.springboot.springbootredisdemo.domain.UserSession;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserSessionManagementImpl implements UserSessionManagement{
    private RedisTemplate<String, UserSession> redisTemplate;

    private HashOperations hashOperations;

    public UserSessionManagementImpl(RedisTemplate<String, UserSession> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(UserSession user) {
        System.out.println(user);

        hashOperations.put("USER", user.getId(), user);
    }

    @Override
    public Map<String, UserSession> findAll() {
        return hashOperations.entries("USER");
    }

    @Override
    public UserSession findById(String id) {
        return (UserSession)hashOperations.get("USER", id);
    }

    @Override
    public void update(UserSession user) {
        save(user);
    }

    @Override
    public void delete(String id) {

        hashOperations.delete("USER", id);
    }

}

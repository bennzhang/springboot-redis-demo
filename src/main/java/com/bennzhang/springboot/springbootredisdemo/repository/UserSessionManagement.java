package com.bennzhang.springboot.springbootredisdemo.repository;

import com.bennzhang.springboot.springbootredisdemo.domain.UserSession;

import java.util.Map;

public interface UserSessionManagement {
    void save(UserSession user);
    Map<String, UserSession> findAll();
    UserSession findById(String id);
    void update(UserSession user);
    void delete(String id);
}

package com.bennzhang.springboot.springbootredisdemo.controller;

import com.bennzhang.springboot.springbootredisdemo.domain.UserSession;
import com.bennzhang.springboot.springbootredisdemo.repository.UserSessionManagementImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserSessionController {
    @Autowired
    private UserSessionManagementImpl userSessionManagementImpl;

    @PostMapping("/add/{id}")
    public ResponseEntity<?> add(@PathVariable("id") final String id,
                                 @RequestBody UserSession session) {
        session.setId(id);
        userSessionManagementImpl.save(session);
        return new ResponseEntity("Successfully added", new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/delete/{id}")
    public Map<String, UserSession> delete(@PathVariable("id") final String id) {
        userSessionManagementImpl.delete(id);
        return all();
    }

    @GetMapping("/get/{id}")
    public UserSession getUserById(@PathVariable("id") final String id) {
        return userSessionManagementImpl.findById(id);
    }

    @GetMapping("/getAll")
    public Map<String, UserSession> all() {
        return userSessionManagementImpl.findAll();
    }

}

package ru.home.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.home.model.User;
import ru.home.service.UserService;

import java.util.List;

@RestController
@Slf4j
public class ApplicationController {

    @Autowired
    private UserService service;

    @PostMapping(value = "/create")
    public ResponseEntity createUser(@Validated @RequestBody User user){

            service.createUser(user);

            return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping(value = "/getListUsers")
    public List<User> getListUsers(){
        return service.getAllUsers();
    }

}

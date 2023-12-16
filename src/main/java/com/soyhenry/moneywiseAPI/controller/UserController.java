package com.soyhenry.moneywiseAPI.controller;

import com.soyhenry.moneywiseAPI.dao.dto.request.UserRequestDto;
import com.soyhenry.moneywiseAPI.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")

public class UserController {
    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping

    public ResponseEntity<String> createUserHandler(@RequestBody UserRequestDto userRequestDto){
        String response = userServiceImpl.createUser(userRequestDto);
        return null;

    }
   /* public User createUser(@RequestBody User user){
    return null;
    }*/
}

package com.soyhenry.moneywiseAPI.controller;

import com.soyhenry.moneywiseAPI.Model.User;
import com.soyhenry.moneywiseAPI.dao.dto.request.UserRequestDto;
import com.soyhenry.moneywiseAPI.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping

    public ResponseEntity<String> createUserHandler(@RequestBody UserRequestDto userRequestDto){
        String response = userService.createUser(userRequestDto);
        return null;

    }
   /* public User createUser(@RequestBody User user){
    return null;
    }*/
}

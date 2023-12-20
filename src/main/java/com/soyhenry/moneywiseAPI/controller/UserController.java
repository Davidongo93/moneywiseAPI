package com.soyhenry.moneywiseAPI.controller;

import com.soyhenry.moneywiseAPI.repository.dto.request.UserRequestDto;
import com.soyhenry.moneywiseAPI.repository.dto.response.UserResponseDto;
import com.soyhenry.moneywiseAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping

    public ResponseEntity<String> createUserHandler(@RequestBody UserRequestDto userRequestDto){
        String response = userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable int id, @RequestBody UserRequestDto userRequestDto) {
        String response = userService.updateUser(id, userRequestDto);
        if (response.equals("User updated Succesfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> responses = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        System.out.println("User has been deleted");
        return ResponseEntity
                .status(HttpStatus.GONE)
                .body("ID from deleted user: " + id);
    }


}

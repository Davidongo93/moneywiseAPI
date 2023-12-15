package com.soyhenry.moneywiseAPI.service.impl;

import com.soyhenry.moneywiseAPI.Model.User;
import com.soyhenry.moneywiseAPI.dao.UserRepository;
import com.soyhenry.moneywiseAPI.dao.dto.request.UserRequestDto;
import com.soyhenry.moneywiseAPI.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String createUser(UserRequestDto userRequestDto) {

    String response = "User created succesfully";

    User user = mapDtoToUser(userRequestDto);
    userRepository.insert(user);
        return null;
    }

    private User mapDtoToUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPass(userRequestDto.getPass());
        return user;
    }
}

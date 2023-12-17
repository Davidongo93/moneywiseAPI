package com.soyhenry.moneywiseAPI.service.impl;

import com.soyhenry.moneywiseAPI.dao.dto.response.UserResponseDto;
import com.soyhenry.moneywiseAPI.model.User;
import com.soyhenry.moneywiseAPI.dao.UserRepository;
import com.soyhenry.moneywiseAPI.dao.dto.request.UserRequestDto;
import com.soyhenry.moneywiseAPI.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    Integer responseInserted = userRepository.insert(user);
    if (responseInserted.equals(0)){
        System.out.println("User wasn't created");
    }
        return response;
    }

    private User mapDtoToUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPass(userRequestDto.getPass());
        return user;
    }
    @Override
    public List<UserResponseDto> getAllUsers(){
        String response = "This is a Users list ordered by ID";
        List<User> users = userRepository.getAll();
        return users.stream().
                map(this::mapUsersToResponseDto).collect(Collectors.toList());
    }

    private UserResponseDto mapUsersToResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPass(user.getPass());
        return userResponseDto;
    }
}

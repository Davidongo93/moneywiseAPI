package com.soyhenry.moneywiseAPI.service.impl;

import com.soyhenry.moneywiseAPI.repository.dto.response.UserResponseDto;
import com.soyhenry.moneywiseAPI.model.User;
import com.soyhenry.moneywiseAPI.repository.UserRepository;
import com.soyhenry.moneywiseAPI.repository.dto.request.UserRequestDto;
import com.soyhenry.moneywiseAPI.repository.exception.DAOException;
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

    String response = "User created successfully";

    User user = mapDtoToUser(userRequestDto);
    Integer responseInserted = userRepository.insert(user);
    if (responseInserted.equals(0)){
        System.out.println("User wasn't created");
    }
    return "User created successfully. \n Your user ID is: " + responseInserted;
    }

    private User mapDtoToUser(UserRequestDto userRequestDto) {
        User user = new User();
        System.out.println("map dto to user REQUEST");
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPass(userRequestDto.getPass());
        return user;
    }
    @Override
    public List<UserResponseDto> getAllUsers(){
        List<User> users = userRepository.getAll();
        return users.stream().
                map(this::mapUsersToResponseDto).collect(Collectors.toList());
    }

    @Override
    public String updateUser(int id, UserRequestDto userRequestDto) {
        String response = "User updated Succesfully";
        User user = mapDtoToUser(userRequestDto);
        Integer responsesUpdated = userRepository.updateUser(id, user);
        return response;
    }

    @Override
    public void deleteUser(int id) throws DAOException {
        userRepository.deleteUser(id);
    }

    private UserResponseDto mapUsersToResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        System.out.println(user.getId());
        userResponseDto.setName("user.getName()");
        userResponseDto.setEmail(user.getEmail());
//      userResponseDto.setPass(user.getPass());
        return userResponseDto;
    }

}

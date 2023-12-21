package com.soyhenry.moneywiseAPI.service;

import com.soyhenry.moneywiseAPI.repository.dto.request.UserRequestDto;
import com.soyhenry.moneywiseAPI.repository.dto.response.UserResponseDto;
import com.soyhenry.moneywiseAPI.repository.exception.DAOException;

import java.util.List;

public interface UserService {
    String createUser(UserRequestDto userRequestDto);

    List<UserResponseDto> getAllUsers();

    String updateUser(int id, UserRequestDto userRequestDto);
    void deleteUser(int id) throws DAOException;
}

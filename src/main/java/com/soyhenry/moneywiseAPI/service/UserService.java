package com.soyhenry.moneywiseAPI.service;

import com.soyhenry.moneywiseAPI.dao.dto.request.UserRequestDto;
import com.soyhenry.moneywiseAPI.dao.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    String createUser(UserRequestDto userRequestDto);

    List<UserResponseDto> getAllUsers();
}

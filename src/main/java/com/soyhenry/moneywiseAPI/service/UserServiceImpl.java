package com.soyhenry.moneywiseAPI.service;

import com.soyhenry.moneywiseAPI.dao.dto.request.UserRequestDto;

public interface UserServiceImpl {
    String createUser(UserRequestDto userRequestDto);
}

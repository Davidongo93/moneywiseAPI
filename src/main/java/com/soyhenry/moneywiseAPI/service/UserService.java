package com.soyhenry.moneywiseAPI.service;

import com.soyhenry.moneywiseAPI.Model.User;
import com.soyhenry.moneywiseAPI.dao.dto.request.UserRequestDto;

public interface UserService {
    String createUser(UserRequestDto userRequestDto);
}

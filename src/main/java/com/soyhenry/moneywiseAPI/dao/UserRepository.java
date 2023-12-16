package com.soyhenry.moneywiseAPI.dao;



import com.soyhenry.moneywiseAPI.model.User;
import com.soyhenry.moneywiseAPI.dao.dto.UserDto;

import java.util.List;

public interface UserRepository {
    void insert(User user);
    List<UserDto> getAll();
    void update();
    void delete(Integer id);

}

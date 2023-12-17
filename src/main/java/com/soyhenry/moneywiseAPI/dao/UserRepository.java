package com.soyhenry.moneywiseAPI.dao;



import com.soyhenry.moneywiseAPI.model.User;
import com.soyhenry.moneywiseAPI.dao.dto.UserDto;

import java.util.List;

public interface UserRepository {
    Integer insert(User user);
    List<User> getAll();
    Integer updateUser(int id, User user);
    void deleteUser(Integer id);


}

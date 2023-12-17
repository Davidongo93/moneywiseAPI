package com.soyhenry.moneywiseAPI.dao;



import com.soyhenry.moneywiseAPI.model.User;
import com.soyhenry.moneywiseAPI.dao.dto.UserDto;

import java.util.List;

public interface UserRepository {
    Integer insert(User user);
    List<User> getAll();
    void update();
    void delete(Integer id);

}

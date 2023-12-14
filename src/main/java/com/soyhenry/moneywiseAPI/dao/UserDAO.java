package com.soyhenry.moneywiseAPI.dao;



import com.soyhenry.moneywiseAPI.Model.User;
import com.soyhenry.moneywiseAPI.dao.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void insert(UserDto userDto);
    List<UserDto> getAll();
    void update();
    void delete(Integer id);

}

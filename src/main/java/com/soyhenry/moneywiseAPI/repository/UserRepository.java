package com.soyhenry.moneywiseAPI.repository;



import com.soyhenry.moneywiseAPI.model.User;
import com.soyhenry.moneywiseAPI.repository.exception.DAOException;

import java.util.List;

public interface UserRepository {
    Integer insert(User user);
    User getUserById(Integer userId);

    List<User> getAll();
    Integer updateUser(int id, User user);
    void deleteUser(Integer id) throws DAOException;


}

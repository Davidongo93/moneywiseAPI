package com.soyhenry.moneywiseAPI.dao.impl;

import com.soyhenry.moneywiseAPI.Model.User;
import com.soyhenry.moneywiseAPI.dao.UserRepository;
import com.soyhenry.moneywiseAPI.dao.dto.UserDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public  class UserRepositoryImplH2 implements UserRepository {
    //SQL sentences:
    private static final String INSERT_USER = "INSERT INTO users (name, email, pass) VALUES (?, ?, ?)";
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImplH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
/*    private final Connection connection;

    public UserRepositoryImplH2(Connection connection) {
        this.connection = connection;
    }*/

    @Override
    public void insert(User user) {
       jdbcTemplate.update(INSERT_USER);
    }

    @Override
    public List<UserDto> getAll () {
        return null;
    }

    @Override
    public void update () {

    }

    @Override
    public void delete (Integer id){

    }

    private User mapDtoToUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

}
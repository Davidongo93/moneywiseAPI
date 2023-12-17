package com.soyhenry.moneywiseAPI.dao.impl;

import com.soyhenry.moneywiseAPI.model.User;
import com.soyhenry.moneywiseAPI.dao.UserRepository;
import com.soyhenry.moneywiseAPI.dao.dto.UserDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public  class UserRepositoryImplH2 implements UserRepository {
    //SQL sentences:
    private static final String INSERT_USER = "INSERT INTO users (name, email, pass) VALUES (?, ?, ?)";
    private static final String GET_USERS_BY_ID = "SELECT * FROM users ORDER BY ID";
    // Connection managed by jdbctemplate.
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImplH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer insert(User user) {
       return jdbcTemplate.update(INSERT_USER,
               user.getName(),
               user.getEmail(),
               user.getPass());

    }


    @Override
    public List<User> getAll () {
            String sql = "SELECT * FROM users ORDER BY ID";
            return jdbcTemplate.query(sql, new UserRowMapper());
        }

        private static class UserRowMapper implements RowMapper<User> {
            @Override
            public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                return user;
            }
    }

    @Override
    public void update () {

    }

    @Override
    public void delete (Integer id){

    }

}
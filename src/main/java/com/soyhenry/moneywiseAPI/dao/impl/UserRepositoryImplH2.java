package com.soyhenry.moneywiseAPI.dao.impl;

import com.soyhenry.moneywiseAPI.model.User;
import com.soyhenry.moneywiseAPI.dao.UserRepository;
import com.soyhenry.moneywiseAPI.dao.dto.UserDto;
import org.springframework.dao.DataAccessException;
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
    private static final String UPDATE_USER = "UPDATE users SET name=?, email=?, pass=? WHERE id=?";
    private static final String GET_USERS_BY_ID = "SELECT * FROM users ORDER BY ID";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
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

            return jdbcTemplate.query(GET_USERS_BY_ID, new UserRowMapper());
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
    public Integer updateUser(int id, User user) {

        return jdbcTemplate.update(UPDATE_USER,
                user.getName(),
                user.getEmail(),
                user.getPass(),
                id);
    }
    @Override
    public void deleteUser (Integer id){
        System.out.println("ID from deleted user: " + id);
        // es necesario manejar una exepcion
        try {
            jdbcTemplate.update(DELETE_USER, id);
        } catch (DataAccessException exception) {
            System.out.println(("error deleting" + id));
        }
        System.out.println("User has been deleted");

    }

}
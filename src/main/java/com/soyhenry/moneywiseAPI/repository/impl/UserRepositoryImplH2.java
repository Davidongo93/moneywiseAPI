package com.soyhenry.moneywiseAPI.repository.impl;

import com.soyhenry.moneywiseAPI.model.User;
import com.soyhenry.moneywiseAPI.repository.UserRepository;
import com.soyhenry.moneywiseAPI.repository.exception.DAOException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public  class UserRepositoryImplH2 implements UserRepository {
    //SQL sentences:
    private static final String INSERT_USER = "INSERT INTO users (name, email, pass) VALUES (?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE users SET name=?, email=?, pass=? WHERE id=?";
    private static final String GET_USERS_BY_ID = "SELECT * FROM users ORDER BY ID";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    private static final String CHECK_USER_EXISTS_QUERY = "SELECT COUNT(*) FROM users WHERE id = ?";
    private static final String SELECT_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";


    // Connection managed by jdbctemplate.
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImplH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer insert(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPass());
            return ps;
        }, keyHolder);

        if (rowsAffected > 0) {
            Integer userId = Objects.requireNonNull(keyHolder.getKey()).intValue();
            user.setId(userId);
        }

        return user.getId();
    }

    public User getUserById(Integer userId) {
        return jdbcTemplate.queryForObject(
                SELECT_USER_BY_ID_QUERY,
                new Object[]{userId},
                (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPass(rs.getString("pass"));
                    // Agrega otros atributos según tu entidad User
                    return user;
                }
        );
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
                System.out.println(user.toString());
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
    public void deleteUser (Integer id) throws DAOException {
        if (!userExists(id)) {
            throw new DAOException("User with ID " + id + " not found. Unable to delete.");
        }
        // NO ES UN BORRADO LOGICO, SI BORRASTE DESAPARECIO :O
        try {
            jdbcTemplate.update(DELETE_USER, id);
            System.out.println("ID from deleted user: " + id);
        } catch (DataAccessException exception) {
            System.out.println(("error deleting" + id));
        }
        System.out.println("User has been deleted");

    }
    private boolean userExists(Integer id) {
        Integer count = jdbcTemplate.queryForObject(CHECK_USER_EXISTS_QUERY, Integer.class, id);
        return count != null && count > 0;
    }

}
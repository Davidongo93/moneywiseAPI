package com.soyhenry.moneywiseAPI.dao.impl;

import com.soyhenry.moneywiseAPI.Model.User;
import com.soyhenry.moneywiseAPI.dao.UserDAO;
import com.soyhenry.moneywiseAPI.dao.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public  class UserDAOImplH2 implements UserDAO {
    //SQL sentences:
    private static final String INSERT_USER = "INSERT INTO users (name, email, pass) VALUES (?, ?, ?)";

    private final Connection connection;

    public UserDAOImplH2(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(UserDto userDto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {

            User user = mapDtoToUser(userDto);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());

            //todo agregar exeption en caso de que el execute update no afecte filas. DAO exeption, se debe crear una carpeta de exepciones dao.
            preparedStatement.executeUpdate();
            System.out.println("User created successfully!");

            // get ID
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(Integer.valueOf(String.valueOf(generatedKeys.getInt(1))));
                    System.out.println(user.toString());
                } else {
                    System.out.println("Could'nt get user ID");
                }
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }



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
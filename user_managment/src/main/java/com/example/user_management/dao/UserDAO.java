package com.example.user_managment.dao;

import com.example.user_managment.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/user_management?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Parola";
    private static final String INSERT_USERS_SQL = "INSERT INTO users  (first_name, last_name, birthdate, phone_number, email) VALUES  (?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "SELECT id,first_name, last_name, birthdate, phone_number, email FROM users WHERE id =?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users;";
    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE users SET first_name = ?, last_name = ?, birthdate = ?, phone_number = ?, email = ? " +
            "WHERE id = ?;";

    public UserDAO() throws SQLException {
    }

    private Connection getConnection() throws SQLException {
       return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        Connection connection = this.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);

        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getBirthdate().toString());
        preparedStatement.setString(4, user.getPhoneNumber());
        preparedStatement.setString(5, user.getEmail());

        System.out.println(preparedStatement);

        preparedStatement.executeUpdate();

        connection.close();
    }

    public User selectUser(int id) throws SQLException {
        User user = null;

        Connection connection = this.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);

        preparedStatement.setInt(1, id);
        System.out.println(preparedStatement);

        String firstName;
        String lastName;
        String birthDate;
        String phoneNumber;
        String email;

        for(ResultSet rs = preparedStatement.executeQuery(); rs.next();
            user = new User(id, firstName, lastName, LocalDate.parse(birthDate),phoneNumber, email)) {

            firstName = rs.getString("first_name");
            lastName = rs.getString("last_name");
            birthDate = rs.getDate("birthdate").toString();
            phoneNumber = rs.getString("phone_number");
            email = rs.getString("email");
        }

        connection.close();

        return user;
    }

    public List<User> selectAllUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();

        Connection connection = this.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);

        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()) {

            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String birthDate = rs.getDate("birthdate").toString();
            String phoneNumber = rs.getString("phone_number");
            String email = rs.getString("email");

            users.add(new User(id, firstName, lastName, LocalDate.parse(birthDate), phoneNumber, email));
        }
        connection.close();

        return users;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean userDeleted;

        Connection connection = this.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);

        statement.setInt(1, id);
        userDeleted = statement.executeUpdate() > 0;

        connection.close();

        return userDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean userUpdated;

        Connection connection = this.getConnection();

        PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);

        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getBirthdate().toString());
        statement.setString(4, user.getPhoneNumber());
        statement.setString(5, user.getEmail());
        statement.setInt(6, user.getId());

        userUpdated = statement.executeUpdate() > 0;
        connection.close();

        return  userUpdated;
    }

}

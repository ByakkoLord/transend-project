package com.example.demo;

import java.sql.*;
import java.util.Arrays;


public class Database {
    private String url;
    private String user;
    private String password;

    private Connection connection;

    public Database(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;

        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch(SQLException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public int getBus() {
        String query = "select * from bus";

        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            return resultSet.getInt("bus");
        } catch(SQLException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return -1;
        }
    }
}

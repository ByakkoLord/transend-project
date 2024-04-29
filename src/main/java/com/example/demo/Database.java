package com.example.demo;

import java.sql.*;
import java.util.Arrays;


public class Database {

    private Connection connection;

    public Database(String url, String user, String password) {

        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch(SQLException e) {
            System.out.println("deu ruim 2: " + e.toString());
        }
    }

    public void sendBus(int id_bus, String route_cod, Boolean pcd_v, int avg_speed, int arrival, int a_date, int info_hr) {
        String query = "insert into bus_info (id_bus, route_cod, arrival, a_date, pcd_v, info_hr, avg_speed) values (" +
                id_bus + ", '" + route_cod + "', " + arrival + ", " + a_date + ", '" + pcd_v + "', " + info_hr + ", " + avg_speed + ")";

        try {
            Statement statement = this.connection.createStatement();
            int rowsAffected = statement.executeUpdate(query);
            connection.close();
            System.out.println(rowsAffected + " linha(s) inserida(s)");
        } catch(SQLException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

}

package com.example.demo;

import java.sql.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class Database {

    private Connection connection;

    public Database(String url, String user, String password) {
        System.out.println("db " + url);
        System.out.println("user " + user);
        System.out.println("pass " + password);

        while (this.connection == null) {
            try {
                try {
                    this.connection = DriverManager.getConnection(url, user, password);
                } catch(SQLException e) {
                    System.out.println("deu ruim 2: " + e.toString());
                }

                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("deu ruim 3: " + e.toString());
            }
        }
    }

    public void sendBus(int id_bus, String route_cod, Boolean pcd_v, int avg_speed, int arrival, int a_date, int info_hr) {
        String query = "insert into bus_info (id_bus, route_cod, arrival, a_date, pcd_v, info_hr, avg_speed) values (" +
                id_bus + ", '" + route_cod + "', " + arrival + ", " + a_date + ", '" + pcd_v + "', " + info_hr + ", " + avg_speed + ")";

        if (this.connection == null) {
            System.out.println("Connection object is null");
            return;
        }

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

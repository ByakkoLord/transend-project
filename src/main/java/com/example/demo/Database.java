package com.example.demo;

import java.sql.*;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class Database {

    private Connection connection;
    ScheduledFuture<?> future;

    public Database(String url, String user, String password) {
        System.out.println("db " + url);
        System.out.println("user " + user);
        System.out.println("pass " + password);

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        future = executorService.scheduleAtFixedRate(() -> this.connectToDatabase(url, user, password), 0, 1, TimeUnit.SECONDS);
    }

    private void connectToDatabase(String url, String user, String password) {
        if (this.connection != null) {
            future.cancel(true);
            return;
        }

        try {
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado com sucesso");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database: " + e);
        }
    }


    public void sendBus(int id_bus, Double Sunday, Double Monday, Double Tuesday, Double Wednesday, Double Thursday, Double Friday, Double Saturday, String route_cod) {
        String query = "INSERT INTO bus_info (id_bus, sunday, monday, tuesday, wednesday, thursday, friday, saturday, route_cod) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        if (this.connection == null) {
            System.out.println("Connection object is null");
            return;
        }

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, id_bus);
            preparedStatement.setDouble(2, Sunday);
            preparedStatement.setDouble(3, Monday);
            preparedStatement.setDouble(4, Tuesday);
            preparedStatement.setDouble(5, Wednesday);
            preparedStatement.setDouble(6, Thursday);
            preparedStatement.setDouble(7, Friday);
            preparedStatement.setDouble(8, Saturday);
            preparedStatement.setString(9, route_cod);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " linha(s) inserida(s)");
        } catch (SQLException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }


    public Double getBus(String route_cod, int dayofWeek) {
        int idResult = 0;
        double sundayResult = 0;
        double mondayResult = 0;
        double tuesdayResult = 0;
        double wednesdayResult = 0;
        double thursdayResult = 0;
        double fridayResult = 0;
        double saturdayResult = 0;

        String query = "SELECT * FROM bus_info WHERE route_cod = ?";
        StringBuilder result = new StringBuilder();

        if (this.connection == null) {
            System.out.println("Connection object is null");
            return null;
        }

        try {
            PreparedStatement pstmt = this.connection.prepareStatement(query);
            pstmt.setString(1, route_cod);
            ResultSet rs = pstmt.executeQuery();

            int[] result1 = {};
            while (rs.next()) {
                result.append("id_bus: ").append(rs.getInt("id_bus")).append(", ");
                result.append("route_cod: ").append(rs.getString("route_cod")).append(", ");

                idResult = rs.getInt("id_bus");
                sundayResult = rs.getDouble("sunday");
                mondayResult = rs.getDouble("monday");
                tuesdayResult = rs.getDouble("tuesday");
                wednesdayResult = rs.getDouble("wednesday");
                thursdayResult = rs.getDouble("thursday");
                fridayResult = rs.getDouble("friday");
                saturdayResult = rs.getDouble("saturday");

            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Failed to execute query: " + e);
        }

        return switch (dayofWeek) {
            case 1 -> sundayResult;
            case 2 -> mondayResult;
            case 3 -> tuesdayResult;
            case 4 -> wednesdayResult;
            case 5 -> thursdayResult;
            case 6 -> fridayResult;
            case 7 -> saturdayResult;
            default -> null;
        };
    }


    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Failed to close db connection: " + e);
        }
    }
}

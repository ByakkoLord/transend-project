package com.example.demo;

import java.sql.*;
import java.time.LocalDate;
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
        System.out.println("id_bus: " + id_bus + " Sunday: " + Sunday + " Monday: " + Monday + " Tuesday: " + Tuesday + " Wednesday: " + Wednesday + " Thursday: " + Thursday + " Friday: " + Friday + " Saturday: " + Saturday + " route_cod: " + route_cod);

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

    public void updateBus(Double Sunday, Double Monday, Double Tuesday, Double Wednesday, Double Thursday, Double Friday, Double Saturday, String route_cod) {
        LocalDate today = LocalDate.now();

        int dayOfMonth = today.getDayOfMonth();
        int daiOfWeek = today.getDayOfWeek().getValue();
        System.out.println("--Dia do mês: " + dayOfMonth);
        System.out.println("--Dia da semana: " + daiOfWeek);
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);

        long sumTime = firstDayOfMonth.until(currentDate, java.time.temporal.ChronoUnit.WEEKS) + 1;
        System.out.println("--Semanas: " + sumTime);
        String query = "";
        if (daiOfWeek == 7) {
            query = "UPDATE bus_info SET sunday = ? WHERE route_cod = ?";
        }else if (daiOfWeek == 1) {
            query = "UPDATE bus_info SET monday = ? WHERE route_cod = ?";
        }else if (daiOfWeek == 2) {
            query = "UPDATE bus_info SET tuesday = ? WHERE route_cod = ?";
        }else if (daiOfWeek == 3) {
            query = "UPDATE bus_info SET wednesday = ? WHERE route_cod = ?";
        }else if (daiOfWeek == 4) {
            query = "UPDATE bus_info SET thursday = ? WHERE route_cod = ?";
        }else if (daiOfWeek == 5) {
            query = "UPDATE bus_info SET friday = ? WHERE route_cod = ?";
        }else if (daiOfWeek == 6) {
            query = "UPDATE bus_info SET saturday = ? WHERE route_cod = ?";
        }

        System.out.println("Query: " + query);

        if (this.connection == null) {
            System.out.println("Connection object is null");
            return;
        }

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);

            if (daiOfWeek == 7){
                preparedStatement.setDouble(1, Sunday);
                preparedStatement.setString(2, route_cod);
            }else if (daiOfWeek == 1){
                preparedStatement.setDouble(1, Monday);
                preparedStatement.setString(2, route_cod);
            }else if (daiOfWeek == 2){
                preparedStatement.setDouble(1, Tuesday);
                preparedStatement.setString(2, route_cod);
            }else if (daiOfWeek == 3){
                preparedStatement.setDouble(1, Wednesday);
                preparedStatement.setString(2, route_cod);
            }else if (daiOfWeek == 4){
                preparedStatement.setDouble(1, Thursday);
                preparedStatement.setString(2, route_cod);
            }else if (daiOfWeek == 5){
                preparedStatement.setDouble(1, Friday);
                preparedStatement.setString(2, route_cod);
            }else if (daiOfWeek == 6){
                preparedStatement.setDouble(1, Saturday);
                preparedStatement.setString(2, route_cod);
            }

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " linha(s) atualizada(s)");
        } catch (SQLException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }


    public Double getBus(String route_cod, int dayofWeek) {
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

            if (!rs.next()) {
                System.out.println("No data found");
                return (double) -1;
            }


            result.append("id_bus: ").append(rs.getInt("id_bus")).append(", ");
            result.append("route_cod: ").append(rs.getString("route_cod")).append(", ");
            System.out.println("Resultado: "+result);
            sundayResult = rs.getDouble("sunday");
            mondayResult = rs.getDouble("monday");
            tuesdayResult = rs.getDouble("tuesday");
            wednesdayResult = rs.getDouble("wednesday");
            thursdayResult = rs.getDouble("thursday");
            fridayResult = rs.getDouble("friday");
            saturdayResult = rs.getDouble("saturday");





            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Failed to execute query: " + e);
        }

        System.out.println("Sunday: " + sundayResult);
        System.out.println("Monday: " + mondayResult);
        System.out.println("Tuesday: " + tuesdayResult);
        System.out.println("Wednesday: " + wednesdayResult);
        System.out.println("Thursday: " + thursdayResult);
        System.out.println("Friday: " + fridayResult);
        System.out.println("Saturday: " + saturdayResult);

        return switch (dayofWeek) {
            case 7 -> sundayResult;
            case 1 -> mondayResult;
            case 2 -> tuesdayResult;
            case 3 -> wednesdayResult;
            case 4 -> thursdayResult;
            case 5 -> fridayResult;
            case 6 -> saturdayResult;
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

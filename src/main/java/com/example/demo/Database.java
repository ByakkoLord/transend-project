package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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


    public void sendBus(int id_bus, String route_cod, boolean pcd_v, String reordered_time) {
        String query = "insert into bus_info (id_bus, route_cod, pcd_v, reordered_time) values(2, 1231, true, 123)";

        if (this.connection == null) {
            System.out.println("Connection object is null");
            return;
        }

        try {
            Statement statement = this.connection.createStatement();
            int rowsAffected = statement.executeUpdate(query);
            System.out.println(rowsAffected + " linha(s) inserida(s)");
        } catch (SQLException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Failed to close db connection: " + e);
        }
    }
}

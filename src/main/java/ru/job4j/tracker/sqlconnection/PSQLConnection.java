package ru.job4j.tracker.sqlconnection;

import ru.job4j.tracker.StartUI;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PSQLConnection implements AutoCloseable {
    private static PSQLConnection instance;
    private final Connection connection = initConnection();

    private PSQLConnection() {
    }

    private Connection initConnection() {
        try (InputStream in = StartUI.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("jdbc.driver"));
            return DriverManager.getConnection(
                    config.getProperty("jdbc.url"),
                    config.getProperty("jdbc.username"),
                    config.getProperty("jdbc.password"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public Connection getConnection() {
        try {
            if (!connection.isClosed()) {
                return connection;
            }
            return initConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static PSQLConnection instOf() {
        if (instance == null) {
            instance = new PSQLConnection();
        }
        return instance;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}

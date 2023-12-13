package com.gfl.mathhelper.equation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/math-helper";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "example";

    public static void saveEquation(String equation, double root) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
            String insertQuery = "INSERT INTO equations (equation, root) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, equation);
                preparedStatement.setDouble(2, root);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

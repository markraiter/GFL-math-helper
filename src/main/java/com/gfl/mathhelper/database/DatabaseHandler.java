package com.gfl.mathhelper.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> findEquationsByRoot(double root) {
        List<String> equations = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
            String selectQuery = "SELECT equation FROM equations WHERE root = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setDouble(1, root);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        equations.add(resultSet.getString("equation"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equations;
    }

    public List<String> findEquationsWithSingleRoot() {
        List<String> equations = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
            String selectQuery = "SELECT equation FROM equations GROUP BY equation HAVING COUNT(root) = 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    equations.add(resultSet.getString("equation"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equations;
    }
}

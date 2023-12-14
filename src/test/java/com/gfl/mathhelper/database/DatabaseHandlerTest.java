package com.gfl.mathhelper.database;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseHandlerTest {

    private static DatabaseHandler databaseHandler;
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    @BeforeAll
    static void setUp() {
        databaseHandler = new DatabaseHandler();
    }

    @AfterAll
    static void tearDown() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void saveEquation_shouldSaveEquationToDatabase() {
        String equation = "2*x+5=17";
        double root = 3.0;

        databaseHandler.saveEquation(equation, root);

        List<String> equationsWithRoot = databaseHandler.findEquationsByRoot(root);
        assertTrue(equationsWithRoot.contains(equation));
    }

    @Test
    void findEquationsByRoot_shouldReturnEquationsWithGivenRoot() {
        String equation1 = "2*x+5=17";
        String equation2 = "3*x-1=8";
        double root = 3.0;

        databaseHandler.saveEquation(equation1, root);
        databaseHandler.saveEquation(equation2, root);

        List<String> equationsWithRoot = databaseHandler.findEquationsByRoot(root);
        assertEquals(2, equationsWithRoot.size());
        assertTrue(equationsWithRoot.contains(equation1));
        assertTrue(equationsWithRoot.contains(equation2));
    }

    @Test
    void findEquationsWithSingleRoot_shouldReturnEquationsWithSingleRoot() {
        String equation1 = "2*x+5=17";
        String equation2 = "3*x-1=8";
        double root1 = 3.0;
        double root2 = 4.0;

        databaseHandler.saveEquation(equation1, root1);
        databaseHandler.saveEquation(equation2, root2);

        List<String> equationsWithSingleRoot = databaseHandler.findEquationsWithSingleRoot();
        assertEquals(1, equationsWithSingleRoot.size());
        assertTrue(equationsWithSingleRoot.contains(equation1) || equationsWithSingleRoot.contains(equation2));
    }
}

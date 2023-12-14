package com.gfl.mathhelper.equation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.gfl.mathhelper.equation.Equation;

public class EquationTest {

    @Test
    void checkParentheses_shouldReturnTrueForCorrectParentheses() {
        Equation equation = new Equation("2*(x+5)=17");
        assertTrue(equation.checkParentheses());
    }

    @Test
    void checkParentheses_shouldReturnFalseForIncorrectParentheses() {
        Equation equation = new Equation("2*(x+5=17");
        assertFalse(equation.checkParentheses());
    }

    @Test
    void checkExpression_shouldReturnTrueForCorrectExpression() {
        Equation equation = new Equation("2*x+5=17");
        assertTrue(equation.checkExpression());
    }

    @Test
    void checkExpression_shouldReturnFalseForIncorrectExpression() {
        Equation equation = new Equation("2**x+5=17");
        assertFalse(equation.checkExpression());
    }

    @Test
    void solveEquation_shouldReturnCorrectValueForGivenX() {
        Equation equation = new Equation("2*x+5=17");
        double x = 3.0;
        assertEquals(11.0, equation.solveEquation(x), 1e-9);
    }

    @Test
    void getEquation_shouldReturnCorrectEquation() {
        Equation equation = new Equation("2*x+5=17");
        assertEquals("2*x+5=17", equation.getEquation());
    }
}

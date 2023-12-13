package com.gfl.mathhelper.equation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Equation {

    private String equation;

    public Equation(String equation) {
        this.equation = equation;
    }

    public boolean checkParentheses() {
        int count = 0;
        for (char c : equation.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }

            if (count < 0) {
                return false;
            }
        }

        return count == 0;
    }

    public boolean checkExpression() {
        Pattern pattern = Pattern.compile("[0-9]+[\\*\\+\\-\\/][0-9]+");
        Matcher matcher = pattern.matcher(equation);

        return !matcher.find();
    }
}

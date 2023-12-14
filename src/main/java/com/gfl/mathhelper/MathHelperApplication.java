package com.gfl.mathhelper;

import com.gfl.mathhelper.database.DatabaseHandler;
import com.gfl.mathhelper.equation.Equation;

public class MathHelperApplication {

	public static void main(String[] args) {

		String equationString = "2*x+5=17";
		double root = 3.0;

		Equation equation = new Equation(equationString);
		DatabaseHandler databaseHandler = new DatabaseHandler();

		if (equation.checkExpression() && equation.checkParentheses()) {
			String actualEquation = equation.getEquation();

			databaseHandler.saveEquation(actualEquation, root);

			System.out.println("Equations with root " + root + ":");
			databaseHandler.findEquationsByRoot(root).forEach(System.out::println);

			System.out.println("\nEquations with single root:");
			databaseHandler.findEquationsWithSingleRoot().forEach(System.out::println);
		}else {
			System.out.println("Non valid equation");
		}
	}
}

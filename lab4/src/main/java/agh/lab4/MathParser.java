package agh.lab4;

import org.mariuszgromada.math.mxparser.Expression;

import javax.swing.*;
import java.text.MessageFormat;
import static agh.lab4.Calculator.main;

public class MathParser {

	private String lastResult;
	private double result;

	//returns output in string form
	public String[] execute(String input) {
		Expression expr = new Expression(input);

		if(expr.checkSyntax()) {
			result = expr.calculate();
			lastResult = Double.toString(result);
			String message = MessageFormat.format(input + "\n{0,number}\n___________\n", result);
			String[] returnarray = {lastResult, message};
			return returnarray;
		}
		else {
			return null;

		}
	}
}

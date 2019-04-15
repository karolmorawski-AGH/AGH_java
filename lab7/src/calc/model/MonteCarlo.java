package calc.model;
import javafx.concurrent.Task;
import javafx.fxml.FXML;

import  static model.Equation.calc;

import java.awt.*;
import java.util.Random;



public class MonteCarlo extends Task<Double> {

	@FXML
	private TextArea resultBox;

	private int progressStatus = 0;
	private int max = 0;

	private int precision = 0;
	private int MIN;
	private int MAX;
	private double baseArea = 0;

	public MonteCarlo(int precision, int MIN, int MAX, double baseArea) {
		this.precision = precision;
		this.MIN = MIN;
		this.MAX = MAX;
		this.baseArea = baseArea;
	}

	/*
	public double calculateIntegral(int precision, int MIN, int MAX, double baseArea) {

		double hitCounter = 0;
		double totalCounter = 0;

		progressStatus = 0;
		max = precision;

		//Range from <MIN, MAX>
		Random random = new Random();

		for(int i = 0; i<precision; i++) {
			double x = MIN + (MAX - MIN) * random.nextDouble();
			double y = MIN + (MAX - MIN) * random.nextDouble();
			totalCounter++;
			if(calc(x,y)) {
				hitCounter++;
				progressStatus = i;
			}

			//DEBUG
			//System.out.println("x: " + x + "\ny: " + y);
		}

		//
		double result = (hitCounter/totalCounter) * baseArea;

		return result;
	}

	 */


	@Override
	protected Double call() throws Exception {

		double hitCounter = 0;
		double totalCounter = 0;

		progressStatus = 0;
		max = precision;

		//Range from <MIN, MAX>
		Random random = new Random();

		for(int i = 0; i<precision; i++) {
			if(this.isCancelled()) {
				updateProgress(max,max);
				break;
			}
			double x = MIN + (MAX - MIN) * random.nextDouble();
			double y = MIN + (MAX - MIN) * random.nextDouble();
			totalCounter++;
			if(calc(x,y)) {
				hitCounter++;
				progressStatus = i;
				updateProgress(progressStatus, max);
			}

			//DEBUG
			//System.out.println("x: " + x + "\ny: " + y);
		}

		Double result = (hitCounter/totalCounter) * baseArea;

		return result;
	}

	@Override protected void succeeded() {
		super.succeeded();
		updateMessage("Done!");
	}

	@Override protected void cancelled() {
		super.cancelled();
		updateMessage("Cancelled!");
	}

	@Override protected void failed() {
		super.failed();
		updateMessage("Failed!");
	}

}

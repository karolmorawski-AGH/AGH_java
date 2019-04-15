package calc.controller;

import calc.model.DrawFunction;
import calc.model.MonteCarlo;
import javafx.beans.property.BooleanProperty;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.net.URL;
import  static model.Equation.calc;
import  calc.model.MonteCarlo.*;

import java.util.ResourceBundle;

public class MainController implements Initializable {

	private boolean isStarted = false;

	@FXML
	private VBox mainwindow;

	@FXML
	private Canvas canvas;

	@FXML
	private Button start;

	@FXML Button stop;

	@FXML
	private ProgressBar progressBar;

	@FXML
	private TextArea resultBox;

	@FXML
	private TextField precisionBox;

	//Start button handler
	public void handleStart()  {

		if(isStarted == true) {
			System.out.println("More than one thread working on canvas is currently not supported (and never will be)");
			return;
		}
		else {
			isStarted = true;
		}


		//Drawing function
		GraphicsContext gc = canvas.getGraphicsContext2D();

		final DrawFunction[] draw = {new DrawFunction(gc)};
		Thread th2 = new Thread(draw[0]);
		th2.setDaemon(true);
		th2.start();
		//drawFunction(gc);

		//Drawing function using calc() method


		//Calculating integral using Monte-Carlo method
		Integer precision;
		if(precisionBox.getText().isEmpty()) {
			precision = 1;
		}
		else {
			precision = Integer.parseInt(precisionBox.getText());
		}

		final MonteCarlo[] mc = {new MonteCarlo(precision, -8, 8, 256)};


		final Double[] result = new Double[1];
		mc[0].setOnSucceeded(workerStateEvent -> { resultBox.setText(mc[0].getValue() + "\n"); isStarted = false; });
		mc[0].setOnFailed(workerStateEvent -> { resultBox.setText("Calculation failed"); isStarted = false; });
		mc[0].setOnCancelled(workerStateEvent -> { resultBox.setText("Calculation cancelled"); isStarted = false; });

		Thread th1 = new Thread(mc[0]);
		th1.setDaemon(true);
		th1.start();

		progressBar.progressProperty().bind(mc[0].progressProperty());

		stop.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(mc[0] == null || draw[0] == null) {
					System.out.println("Tasks not running");
					return;
				}
				System.out.println("Stop Pressed");
				mc[0].cancel();
				draw[0].cancel();
				mc[0] = null;
				draw[0] = null;
			}
		});


	}



	//Initializer
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		//Sets canvas background color
		GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.setFill(Color.rgb(0,0,50));
		gc.fillRect(gc.getCanvas().getLayoutX(),
				gc.getCanvas().getLayoutY(),
				gc.getCanvas().getWidth(),
				gc.getCanvas().getHeight());

		precisionBox.setText("10000000");
	}

}

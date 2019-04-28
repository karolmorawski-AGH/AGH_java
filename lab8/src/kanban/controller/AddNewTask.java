package kanban.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import kanban.model.Priority;
import kanban.model.State;

import java.time.LocalDate;

public class AddNewTask {

	@FXML
	TextField titleText;
	@FXML
	SplitMenuButton priorityButton;

	@FXML
	DatePicker datePickerTask;

	@FXML
	ListView<Task> ToDoList;

	@FXML
	TextArea addnotationText;

	@FXML
	Button addButton;

	public Priority priorityLevel;


	public void setLow(){
		priorityLevel = Priority.LOW;
		priorityButton.setText("Low");
	};
	public void setMediumPriority(){
		priorityLevel =Priority.MEDIUM;
		priorityButton.setText("Medium");
	};
	public void setHighPriority(){
		priorityLevel =Priority.HIGH;
		priorityButton.setText("High");
	};


	public void addButton(){

		Stage stage =(Stage) addButton.getScene().getWindow();
		stage.setResizable(false);
		stage.getIcons().add(new Image("file:../../img/logo.png"));
		String title= titleText.getText();
		LocalDate deadline = datePickerTask.getValue();
		String moreinfo = addnotationText.getText();
		State state = State.TODO;
		Task task =new Task(priorityLevel,deadline,title,moreinfo, state);
		try{

			if(title.equals("")){
				throw (new IllegalArgumentException());
			}

			MainController.todoList.add(task);

		} catch(IllegalArgumentException e){
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Task name is required");
			alert.setContentText("placeholder");
			alert.showAndWait();

		}
		stage.close();

	}

}

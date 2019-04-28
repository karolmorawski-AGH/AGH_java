package kanban.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import kanban.model.Priority;
import kanban.model.State;

import java.time.LocalDate;

public class EditController {

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
	Button editButton;


	public Priority prioritylevel;


	public void setLowPriority(){
		prioritylevel = Priority.LOW;
		priorityButton.setText("Low");
	};
	public void setMediumPriority(){
		prioritylevel =Priority.MEDIUM;
		priorityButton.setText("Medium");
	};
	public void setHighPriority(){
		prioritylevel =Priority.HIGH;
		priorityButton.setText("High");
	};

	private Task element;

	public void setEdit(Task el) {
		element = el;

		titleText.setText(el.getTitle());
		addnotationText.setText(el.getMoreinfo());
		datePickerTask.setValue(el.getDeadline());

		if(el.getPriority() == Priority.HIGH) {
			setHighPriority();
		}
		else if(el.getPriority() == Priority.MEDIUM) {
			setMediumPriority();
		}
		else {
			setLowPriority();
		}
	}

	public void editButton(){
		Stage stage =(Stage) editButton.getScene().getWindow();
		stage.setResizable(false);
		String title= titleText.getText();
		LocalDate deadline = datePickerTask.getValue();
		String moreinfo = addnotationText.getText();
		State state = State.TODO;
		Task task =new Task(prioritylevel,deadline,title,moreinfo, state);
		try{

			if(title.equals("")){
				throw (new IllegalArgumentException());
			}

			element = task;
			System.out.println(element.getCsv());

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
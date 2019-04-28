package kanban.controller;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import kanban.model.IO;
import kanban.model.Priority;
import kanban.model.State;
import kanban.model.TaskList;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {


	@FXML
	ListView<Task> ToDoList;
	public static ObservableList<Task> todoList = FXCollections.observableArrayList();


	@FXML
	ListView<Task> InProgress;
	public static ObservableList<Task> inProgressList = FXCollections.observableArrayList();

	@FXML
	ListView<Task> Done;
	public static ObservableList<Task> doneList = FXCollections.observableArrayList();


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {


		//Context menus
		ContextMenu tasksContext = new ContextMenu();
		ContextMenu inProgressContext = new ContextMenu();
		ContextMenu doneContext = new ContextMenu();

		//Show
		//------------------------------------------------------------------------
		MenuItem show = new MenuItem();
		show.textProperty().bind(Bindings.format("Show details"));

		show.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int buff = ToDoList.getSelectionModel().getSelectedIndex();
				if(buff!=-1) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Details");
					alert.setHeaderText("Task information");
					alert.setContentText("Task: " + todoList.get(buff).getTitle() + "\nPriority: " + todoList.get(buff).getPriority() + "\nDue date: " + todoList.get(buff).getDeadline() + "\nDescription: " + todoList.get(buff).getMoreinfo());
					alert.showAndWait();
				}
			}
		});
		//------------------------------------------------------------------------

		//Delete
		//------------------------------------------------------------------------
		MenuItem delete = new MenuItem();
		delete.textProperty().bind(Bindings.format("Delete"));

		delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int buff3=ToDoList.getSelectionModel().getSelectedIndex();
				if(buff3 != -1) {
					todoList.remove(ToDoList.getSelectionModel().getSelectedIndex());
					ToDoList.refresh();
				}
			}
		});
		//------------------------------------------------------------------------

		//Edit
		MenuItem edit = new MenuItem();
		edit.textProperty().bind(Bindings.format("Edit"));

		edit.setOnAction(event ->{

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/edit.fxml"));
			Parent root = null;
			try {
				root = fxmlLoader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			EditController controller = fxmlLoader.getController();
				int buff = ToDoList.getSelectionModel().getSelectedIndex();
				controller.setEdit(todoList.get(buff));
				Stage stage = new Stage();
				Scene a = new Scene(root);
				stage.setScene(a);
				stage.setTitle("Edit Task");

				stage.showAndWait();
				ToDoList.refresh();
		});


		//To In-progress
		MenuItem progress = new MenuItem();
		progress.textProperty().bind(Bindings.format("Move to In-Progress"));

		progress.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int buff1= ToDoList.getSelectionModel().getSelectedIndex();
				if(buff1!=-1) {
					Task temp = todoList.get(buff1);
					temp.setState(State.INPROGRESS);
					inProgressList.add(temp);
					todoList.remove(buff1);
					ToDoList.refresh();
				}
			}

		});


		//Tooltip
		ToDoList.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
			@Override
			public ListCell<Task> call(ListView<Task> listGroupListView) {
				//final Label leadLbl = new Label();
				final Tooltip tooltip = new Tooltip();

				final ListCell<Task> cell = new ListCell<Task>() {
					@Override
					public void updateItem(Task item, boolean empty) {
						super.updateItem(item, empty);
						if(empty) {
							setText(null);
						}
						if (item != null) {
							setText(item.toString());
							tooltip.setText(item.getMoreinfo());
							setTooltip(tooltip);
						}
						else {
							setTooltip(null);
						}
					}
				};
				return cell;
			}
		});


		//INPROGRESS METHODS

		//SHOW IN PROGRESS
		MenuItem showProgress = new MenuItem();
		showProgress.textProperty().bind(Bindings.format("Show details"));

		showProgress.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int buff = InProgress.getSelectionModel().getSelectedIndex();
				if(buff!=-1) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Details");
					alert.setHeaderText("Task information");
					alert.setContentText("Task: " + inProgressList.get(buff).getTitle() + "\nPriority: " + inProgressList.get(buff).getPriority() + "\nDue date: " + inProgressList.get(buff).getDeadline() + "\nDescription: " + inProgressList.get(buff).getMoreinfo());
					alert.showAndWait();
				}
			}
		});


		//DELETE IN PROGRESS
		MenuItem deleteProgress = new MenuItem();
		deleteProgress.textProperty().bind(Bindings.format("Delete"));

		deleteProgress.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int buffer3 = InProgress.getSelectionModel().getSelectedIndex();
				if(buffer3!=-1) {
					inProgressList.remove(buffer3);
					InProgress.refresh();
				}
			}
		});


		//To Done
		//Only in In Progress
		MenuItem toDone = new MenuItem();
		toDone.textProperty().bind(Bindings.format("Move to Done"));

		toDone.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int buffer5= InProgress.getSelectionModel().getSelectedIndex();
				if(buffer5!=-1) {
					Task temp = inProgressList.get(buffer5);
					temp.setState(State.DONE);
					doneList.add(temp);
					inProgressList.remove(buffer5);
					InProgress.refresh();
				}
			}

		});

		InProgress.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
			@Override
			public ListCell<Task> call(ListView<Task> listGroupListView) {
				final Label leadLbl = new Label();
				final Tooltip tooltip = new Tooltip();

				final ListCell<Task> cell = new ListCell<Task>() {
					@Override
					public void updateItem(Task item, boolean empty) {
						super.updateItem(item, empty);
						if(empty) {
							setText(null);
						}
						if (item != null) {
							//leadLbl.setText(item.getTitle());
							setText(item.toString());
							tooltip.setText(item.getMoreinfo());
							setTooltip(tooltip);
						}

					}
				};

				return cell;
			}
		});


		MenuItem editProgress = new MenuItem();
		editProgress.textProperty().bind(Bindings.format("Edit"));

		editProgress.setOnAction(event ->{
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/edit.fxml"));
				Parent root = fxmlLoader.load();
				EditController controller = fxmlLoader.getController();
				int index = InProgress.getSelectionModel().getSelectedIndex();
				controller.setEdit(inProgressList.get(index));
				Stage stage = new Stage();
				Scene a = new Scene(root);
				stage.setScene(a);
				stage.setTitle("Edit Task");

				stage.showAndWait();
				InProgress.refresh();

			} catch (Exception e){
				System.out.println(e.getCause().toString());
			}

		});

		//DONE METHODS

		//SHOW IN DONE
		MenuItem showDone = new MenuItem();
		showDone.textProperty().bind(Bindings.format("Show details"));

		showDone.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int buff = Done.getSelectionModel().getSelectedIndex();
				if(buff!=-1) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Details");
					alert.setHeaderText("Task information");
					alert.setContentText("Task: " + doneList.get(buff).getTitle() + "\nPriority: " + doneList.get(buff).getPriority() + "\nDue date: " + doneList.get(buff).getDeadline() + "\nDescription: " + doneList.get(buff).getMoreinfo());
					alert.showAndWait();
				}
			}
		});


		//DELETE IN DONE
		MenuItem deleteDone = new MenuItem();
		deleteDone.textProperty().bind(Bindings.format("Delete"));

		deleteDone.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int buffer3=Done.getSelectionModel().getSelectedIndex();
				if(buffer3!=-1) {
					doneList.remove(buffer3);
					Done.refresh();
				}
			}
		});

		Done.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
			@Override
			public ListCell<Task> call(ListView<Task> listGroupListView) {
				final Label leadLbl = new Label();
				final Tooltip tooltip = new Tooltip();

				final ListCell<Task> cell = new ListCell<Task>() {
					@Override
					public void updateItem(Task item, boolean empty) {
						super.updateItem(item, empty);
						if(empty) {
							setText(null);
						}
						if (item != null) {
							//leadLbl.setText(item.getTitle());
							setText(item.toString());
							tooltip.setText(item.getMoreinfo());
							setTooltip(tooltip);
						}

					}
				};

				return cell;
			}
		});

		MenuItem editDone = new MenuItem();
		editDone.textProperty().bind(Bindings.format("Edit"));

		editDone.setOnAction(event ->{
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/edit.fxml"));
				Parent root = fxmlLoader.load();
				EditController controller = fxmlLoader.getController();
				int index = Done.getSelectionModel().getSelectedIndex();
				controller.setEdit(doneList.get(index));
				Stage stage = new Stage();
				Scene a = new Scene(root);
				stage.setScene(a);
				stage.setTitle("Edit Task");

				stage.showAndWait();
				Done.refresh();

			} catch (Exception e){
				System.out.println(e.getCause().toString());
			}

		});

		//To Do
		ToDoList.setContextMenu(tasksContext);
		tasksContext.getItems().addAll(show, edit,  delete, progress);
		ToDoList.setItems(todoList);

		//In progress
		InProgress.setContextMenu(inProgressContext);
		inProgressContext.getItems().addAll(showProgress, deleteProgress, toDone);
		InProgress.setItems(inProgressList);

		//Done
		Done.setContextMenu(doneContext);
		doneContext.getItems().addAll(showDone,  deleteDone);
		Done.setItems(doneList);
	}

	//About
	public void about(){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		Image image = new Image("file:../../img/meme.gif");
		ImageView imageView = new ImageView(image);
		alert.setGraphic(imageView);
		alert.setTitle("About");
		alert.setHeaderText("Information about authors");
		alert.setContentText("Author: JA");
		alert.showAndWait();
	}

	//closes application
	public void close() {
		Platform.exit();
		System.exit(0);
	}

	public void addTask() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../view/add-new-task.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Add Task");
		stage.show();
	}

	//LAB8

	//import
	public void importTask() {

		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter csvfilter = new FileChooser.ExtensionFilter("CSV File", "*.csv");
		fileChooser.setTitle("Import Tasks");
		fileChooser.getExtensionFilters().add(csvfilter);
		File file = fileChooser.showOpenDialog(new Stage());

		//If filepath not selected
		if(file == null) {
			System.out.println("No filepath selected");
			return;
		}


		try {
			IO getList = new IO(file);
			List<String> csvStringList = getList.readLine();
			TaskList<Task> allTasksList = new TaskList<>();

			for(int i =0; i<csvStringList.size(); i++) {
				allTasksList.addReconstructedObjectToList(csvStringList.get(i));
			}



			//adding to current lists
			for(int i =0; i< allTasksList.size(); i++) {
					if(allTasksList.get(i).getState() == State.TODO) {
						todoList.add(allTasksList.get(i));
					}
				else if(allTasksList.get(i).getState() == State.INPROGRESS) {
						inProgressList.add(allTasksList.get(i));
				}
				else {
						doneList.add(allTasksList.get(i));
				}

			}


		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	//export
	public void exportTask() throws IOException {

		//Filechooser
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter csvfilter = new FileChooser.ExtensionFilter("CSV File", "*.csv");

		fileChooser.setTitle("Save Image");
		fileChooser.getExtensionFilters().add(csvfilter);
		File file = fileChooser.showSaveDialog(new Stage());

		//If filepath not selected
		if(file == null) {
			System.out.println("No filepath selected");
			return;
		}

		//elo
		TaskList<Task> taskList = new TaskList<>();
		taskList.addAll(todoList);
		taskList.addAll(inProgressList);
		taskList.addAll(doneList);

		//Writing to file
		BufferedWriter bw = null;

		try {
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			for(int i = 0; i < taskList.size(); i++) {
				bw.write(taskList.get(i).getCsv());
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(bw != null) {
				bw.close();
			}
		}
	}

	//Open
	public void openTask() throws IOException {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter csvfilter = new FileChooser.ExtensionFilter("Serialized object data", "*.ser");
		fileChooser.setTitle("Import Tasks");
		fileChooser.getExtensionFilters().add(csvfilter);
		File file = fileChooser.showOpenDialog(new Stage());

		//If filepath not selected
		if(file == null) {
			System.out.println("No filepath selected");
			return;
		}

		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			TaskList<Task> testTaskList = (TaskList<Task>) in.readObject();
			in.close();
			fileIn.close();

			//adding to current lists
			for(int i =0; i< testTaskList.size(); i++) {
				if(testTaskList.get(i).getState() == State.TODO) {
					todoList.add(testTaskList.get(i));
				}
				else if(testTaskList.get(i).getState() == State.INPROGRESS) {
					inProgressList.add(testTaskList.get(i));
				}
				else {
					doneList.add(testTaskList.get(i));
				}
			}


		}
		catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	//Save
	public void saveTask() throws IOException {
		//Filechooser
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter csvfilter = new FileChooser.ExtensionFilter("Serialized object data", "*.ser");

		fileChooser.setTitle("Save Image");
		fileChooser.getExtensionFilters().add(csvfilter);
		File file = fileChooser.showSaveDialog(new Stage());

		//If filepath not selected
		if(file == null) {
			System.out.println("No filepath selected");
			return;
		}

		//elo
		TaskList<Task> taskList = new TaskList<>();
		taskList.addAll(todoList);
		taskList.addAll(inProgressList);
		taskList.addAll(doneList);


		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			ObjectOutputStream outputStream = new ObjectOutputStream((fileOutputStream));
			outputStream.writeObject(taskList);
			outputStream.flush();
			outputStream.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}

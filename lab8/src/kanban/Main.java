package kanban;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application {

	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("view/main-window.fxml"));
		primaryStage.setTitle("Kanban");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("file:../../img/logo.png"));

		primaryStage.show();
	}



	public static void main(String[] args) {
		launch(args);
	}

}



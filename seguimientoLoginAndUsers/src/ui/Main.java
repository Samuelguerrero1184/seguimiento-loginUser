package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Classroom;



public class Main extends Application {
	
	private Classroom classroom;
	private ClassroomGUI classroomGUI;
	
	
	public Main () {
		classroom = new Classroom();
		classroomGUI = new ClassroomGUI(classroom);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("mainPane.fxml"));
		
		fxmlloader.setController(classroomGUI);
		
		Parent root = fxmlloader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Classroom");
		primaryStage.show();
	}

}

package ui;

import model.Classroom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.UserAccount;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class ClassroomGUI {
	
	String selectedCareers = "";
	Stage stage = new Stage();
	
	private Classroom classroom;
	
	
	public ClassroomGUI(Classroom cm) {
    	classroom = cm;    	
	}

	@FXML
	private BorderPane mainPane;

	@FXML
    private TextField txtUsername;

    @FXML
    private PasswordField pfPassword;

    @FXML
    void loginUser(ActionEvent event) {

    }

    @FXML
    void signupWindow(ActionEvent event) throws IOException {
    	FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("register.fxml"));
    	
    	fxmlloader.setController(this);
    	
    	Parent registerPane = fxmlloader.load();
    	mainPane.getChildren().clear();
    	mainPane.setCenter(registerPane);

    	/**
    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
    	stage.setScene(scene);
    	stage.setTitle("New Account");
    	stage.show();
    	*/
    }
    
  //list-------------------------------------------------------------
    @FXML
    private TreeTableView<UserAccount> tvContent;
    
    @FXML
    private Label tcProfile;

    @FXML
    private Button tcLogout;

    @FXML
    private TreeTableColumn<UserAccount, String> tcUsername;

    @FXML
    private TreeTableColumn<UserAccount, String> tcGender;

    @FXML
    private TreeTableColumn<UserAccount, String> tcCareer;
    
    
    //register-------------------------------------------------------
    @FXML
    private TextField suUsername;

    @FXML
    private PasswordField suPassword;

    @FXML
    private ToggleGroup RadioButtons;

    @FXML
    private CheckBox cbxTel;

    @FXML
    private CheckBox cbxOther;

    @FXML
    private CheckBox cbxSoft;

    @FXML
    private DatePicker dateAge;


    @FXML
    void back2Login(ActionEvent event) throws IOException {
    	String user = classroom.getUserAcc().get(0).getUser();
    	String pass = classroom.getUserAcc().get(0).getPasscode();
    	if(user.equals(suUsername.getText())) {
    		if(pass.equals(suPassword.getText())) {
    			loadTableview();
    		}
    		else {
        		Alert alert = new Alert(AlertType.WARNING);
        		alert.setTitle("Wrong username or password");
        		alert.setHeaderText("Wrong username or password");
        		alert.setContentText("Wrong username or password");
        		alert.showAndWait();
        		suUsername.clear();
        		suPassword.clear();
        	}
    	}
    	else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Wrong username or password");
    		alert.setHeaderText("Wrong username or password");
    		alert.setContentText("Wrong username or password");
    		alert.showAndWait();
    		suUsername.clear();
    		suPassword.clear();
    	}
    }
    
    @FXML
    public void createNewAccount(ActionEvent event) {
    	if(cbxTel.isSelected()) {
    		selectedCareers += cbxTel.getText();
    		if(cbxSoft.isSelected()) {
    			selectedCareers += ", ";
    			selectedCareers +=cbxSoft.getText();
    			if(cbxOther.isSelected()) {
    				selectedCareers += ", ";
        			selectedCareers +=cbxOther.getText();
    			}
    		}
    	}
    	else if(cbxSoft.isSelected()) {
			selectedCareers +=cbxSoft.getText();
			if(cbxOther.isSelected()) {
				selectedCareers += ", ";
    			selectedCareers +=cbxOther.getText();
			}
    	}
    	else if (cbxOther.isSelected()) {
			selectedCareers +=cbxOther.getText();
    	}
    	else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Missing data");
    		alert.setHeaderText("Missing data");
    		alert.setContentText("Missing info :\nNo career chosen");
    		alert.showAndWait();
    	}
    	classroom.addAccount(suUsername.getText(), suPassword.getText(), RadioButtons.getSelectedToggle().toString(), selectedCareers);

    }
    
    private void initializeTableview() {
    	ObservableList<UserAccount> observableList;
    	observableList = FXCollections.observableArrayList(classroom.getUserAcc());
    	
		tvContent.setItems(observableList);
		tcUsername.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("username")); 
		tcGender.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("gender")); 
		tcCareer.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("career")); 
    }
    
    public void loadTableview() throws IOException {
    	FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("accountList.fxml"));
    	
    	fxmlloader.setController(this);
    	
    	Parent listPane = fxmlloader.load();
    	tcProfile.setText(suUsername.getText());
    	mainPane.getChildren().clear();
    	mainPane.setCenter(listPane);
    	initializeTableview();
    }

}

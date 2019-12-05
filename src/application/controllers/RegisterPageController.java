package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.App;
import application.models.Student;
import application.services.UserManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class RegisterPageController implements Initializable {
  @FXML private TextField txtUsername, txtPassword, txtFirstName, txtLastName, txtEmailAddress;
  @FXML private DatePicker dtDOB;
  @FXML private ComboBox<String> cbGender;
  @FXML private TextField txtAddress, txtCity, txtZipCode;
  @FXML private ComboBox<String> cbState;
  @FXML private ComboBox<String> cbMajor;
  @FXML private Label lblError;
  
  App context;
  
  public void setContext(App context) {
    this.context = context;
  }
  
  // back to login
  public void onBackLinkClick(ActionEvent e) {
    context.navigateToLogin();
  }
  
  public void onRegisterButtonClick(ActionEvent e) {
    Student user = new Student();
    user.setUsername(txtUsername.getText());
    user.setPassword(txtPassword.getText());
    user.setName(txtFirstName.getText() + txtLastName.getText());
    user.setEmailAddress(txtEmailAddress.getText());
    // user.setAge(Date.now - dtDOB);
    //user.setGender(cbGender.getValue().toString());
    //user.setAddress(txtAddress.getText() + txtCity.getText() + cbState.getValue().toString() + txtZipCode.getText());
    //user.setMajor(cbMajor.getValue().toString());
   
    UserManager userManager = context.getUserManager();
    if (!userManager.userExists(txtUsername.getText())) {
      userManager.addUser(user);
      context.navigateToLogin();
    }
    else {
      lblError.setText("Username already taken, pick another one.");
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    cbGender.setItems(FXCollections.observableArrayList("Male", "Female"));
    cbMajor.setItems(FXCollections.observableArrayList("Computer Science", "EE", "Math"));
    cbState.setItems(FXCollections.observableArrayList("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"));
  }
}

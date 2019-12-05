package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginPageController implements Initializable {
  @FXML private TextField txtUsername, txtPassword;
  @FXML private Label lblError;
  
  private App context;
  
  public void setContext(App context) {
    this.context = context;
  }
    
  public void onRegisterLinkClick(ActionEvent e) {
    context.navigateToRegistration();
  }
  
  public void onLoginButtonClick(ActionEvent e) {
    
    // check if the user is registered;  validate their username and password
    if (context.getUserManager().assertLogin(txtUsername.getText(), txtPassword.getText())) {
      context.setActiveUser(context.getUserManager().getUser(txtUsername.getText()));
      context.navigateToCourseBrowser();
    }
    else {
      lblError.setText("Invalid username and/or password");
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    
  }
  
}

package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.App;
import application.models.Admin;
import application.models.Course;
import application.models.Student;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class CourseCellController extends ListCell<Course> implements Initializable {
  @FXML private Label lblCode, lblDays, lblTime, lblPrereq, lblInstructor;
  @FXML private Text txtTitle;
  @FXML private TextFlow txtDescription;
  @FXML private Button btnEnroll;
  @FXML private Hyperlink linkEdit, linkRemove;
  @FXML private BorderPane cell;
  
  private App context;
  
  public CourseCellController(App context) {
    try {
      this.context = context;
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/CourseItem.fxml"));
      loader.setController(this);
      loader.load();
    }
    catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  public void onEditCourseLinkClick(ActionEvent e) {
    context.setSelectedCourse(getItem());
    context.navigateToEditCourse();
  }
  
  public void onRemoveCourseLinkClick() {
    context.getCourseManager().removeCourse(getItem().getID());
    getListView().refresh();
    //context.navigateToCourseBrowser();
  }
  
  private boolean isEnrolled() {
    Student s = (Student) context.getActiveUser();
    Course c = getItem();
    
    return s.isEnrolled(c.getID());
  }
  
  private EventHandler<ActionEvent> courseAction = new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent e) {
      Student s = (Student) context.getActiveUser();
      Course c = getItem();
      
      String btnText = "";
      // if not enrolled, then enroll into class and update button text to say unenroll
      if (!isEnrolled()) {
        s.enrollToCourse(getItem());
        btnText = "Unenroll";
      }
      else {
        s.unenrollFromCourse(c.getID());
        btnText = "Enroll";
      }
      
      ((Button) e.getSource()).setText(btnText);
    }
  };
  
  @Override
  protected void updateItem(Course item, boolean empty) {
    super.updateItem(item, empty);
    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    if (empty) {
      setGraphic(null);
    }
    else {
      lblCode.setText(item.getCode());
      txtTitle.setText(item.getTitle());
      txtDescription.getChildren().add(new Text(item.getDescription()));
      lblDays.setText("days: " + item.getDays());
      lblTime.setText("time: " + item.getTime());
      lblPrereq.setText("prereq: " + item.getPrereq());
      lblInstructor.setText("instructor: " + item.getInstructor());
      
      // admin can add or edit courses, but can't enroll
      if (context.getActiveUser() instanceof Admin) {
        btnEnroll.setDisable(true);
        linkEdit.setVisible(true);
        linkRemove.setVisible(true);
      }
      // students can enroll, but can't add or edit
      else {
        btnEnroll.setVisible(true);
        linkEdit.setVisible(false);
        linkRemove.setVisible(false);
        
        // set up button text as items load
        if (isEnrolled()) {
          btnEnroll.setText("Unenroll"); 
        }
        else {
          btnEnroll.setText("Enroll");
        }
      }
      
      btnEnroll.setOnAction(courseAction);
      
      setGraphic(cell);
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
  }
}

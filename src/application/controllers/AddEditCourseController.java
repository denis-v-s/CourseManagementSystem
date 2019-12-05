package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.App;
import application.models.Course;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class AddEditCourseController implements Initializable {
  @FXML private TextField txtCode, txtInstructor, txtTitle, txtPrereq, txtTextbook, txtStartTime, txtEndTime;
  @FXML private TextArea txtDescription;
  @FXML private CheckBox chkMonday, chkTuesday, chkWednesday, chkThursday, chkFriday;
  
  private App context;
  
  public void setContext(App context) {
    this.context = context;
    
    if (context.getSelectedCourse() != null) {
      Course course = context.getSelectedCourse();
      txtCode.setText(course.getCode());
      txtInstructor.setText(course.getInstructor());
      txtTitle.setText(course.getTitle());
      txtDescription.setText(course.getDescription());
      txtPrereq.setText(course.getPrereq());
      txtTextbook.setText(course.getTextbook());
      txtStartTime.setText(course.getStartTime());
      txtEndTime.setText(course.getEndTime());
      setCheckboxSelections(course);
    }
  }
  
  public void onSaveButtonClick() {
    Course course = new Course();
    course.setCode(txtCode.getText());
    course.setTitle(txtTitle.getText());
    course.setInstructor(txtInstructor.getText());
    course.setDescription(txtDescription.getText());
    course.setPrereq(txtPrereq.getText());
    course.setTextbook(txtTextbook.getText());
    course.setStartTime(txtStartTime.getText());
    course.setEndTime(txtEndTime.getText());
    course.setDays(checkBoxSelectionToString());
    
    Course selectedCourse = context.getSelectedCourse();
    
    // if course already exists, then it's an edit
    if (selectedCourse != null && context.getCourseManager().courseExists(selectedCourse.getID())) {
      context.getCourseManager().editCourse(selectedCourse.getID(), course);
    }
    // if course is new, then add it
    else if (!txtCode.getText().isEmpty()) {
      context.getCourseManager().addCourse(course);
    }
    
    context.setSelectedCourse(null);
    context.navigateToCourseBrowser();
  }
  
  public void onBackLinkClick() {
    context.navigateToCourseBrowser();
  }
  
  private String checkBoxSelectionToString() {
    String days = "";
    if (chkMonday.isSelected()) {
      days += "M";
    }
    if (chkTuesday.isSelected()) {
      days += "T";
    }
    if (chkWednesday.isSelected()) {
      days += "W";
    }
    if (chkThursday.isSelected()) {
      days += "R";
    }
    if (chkFriday.isSelected()) {
      days += "F";
    }
    
    return days;
  }
  
  private void setCheckboxSelections(Course c) {
    for (String day : c.getDays().split("")) {
      if (day.equals("M")) {
        chkMonday.setSelected(true);
      }
      if (day.equals("T")) {
        chkTuesday.setSelected(true);
      }
      if (day.equals("W")) {
        chkWednesday.setSelected(true);
      }
      if (day.equals("R")) {
        chkThursday.setSelected(true);
      }
      if (day.equals("F")) {
        chkFriday.setSelected(true);
      }
    }
  }
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    
  }

}

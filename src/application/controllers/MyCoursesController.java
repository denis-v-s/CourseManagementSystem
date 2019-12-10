package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.App;
import application.models.*;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class MyCoursesController implements Initializable {
  @FXML private TableView<MyCourse> tblMyCourses;
  @FXML private TableColumn<MyCourse, String> colCode;
  @FXML private TableColumn<MyCourse, String> colTitle;
  @FXML private TableColumn<MyCourse, Integer> colCredit;
  @FXML private TableColumn<MyCourse, String> colDays;
  @FXML private TableColumn<MyCourse, String> colStart;
  @FXML private TableColumn<MyCourse, String> colEnd;
  @FXML private TableColumn<MyCourse, Status> colStatus;
  @FXML private TableColumn<MyCourse, Grade> colGrade;
  @FXML private TableColumn<MyCourse, String> colRemove;
  @FXML private TextField txtTitle;
  @FXML private Label txtGPA;
  
  private App context;
  
  public void setContext(App context) {
    this.context = context;
    Student s = ((Student) context.getActiveUser());
    
    ObservableList<MyCourse> courses = FXCollections.observableArrayList(s.getMyCourses());
    FilteredList<MyCourse> filteredCourses = new FilteredList<>(courses);
    
    tblMyCourses.setItems(filteredCourses);
    
    // update GPA
    txtGPA.setText(String.format("GPA: %.2f", s.getGPA()));
    
    // set up the search feature
    txtTitle.textProperty().addListener(observable -> {
      String searchTerm = txtTitle.getText().toUpperCase();
      
      if (searchTerm.isEmpty()) {
        filteredCourses.setPredicate(c -> true);
      }
      else {
        filteredCourses.setPredicate(c -> c.getCourse().getTitle().toUpperCase().contains(searchTerm));
      }
    });
    
    setUpTableColumns();
  }
  
  public void onBackLinkClick() {
    context.navigateToCourseBrowser();
  }
  
  // Factories for each column
  private void setUpTableColumns() {
    // Course Code column
	  colCode.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCourse().getCode()));
    
    // Title column
	colTitle.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCourse().getTitle()));
    
    // Credit column
	colCredit.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getCourse().getCredit()));
    
    // Days column
	colDays.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCourse().getDays()));
    
    // Start Time column
	colStart.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCourse().getStartTime()));
    
    // End Time column
	colEnd.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCourse().getEndTime()));
    
    // Status column
	colStatus.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getStatus()));
    
    // Grade column
	colGrade.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getGrade()));
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    
  }
}

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
  
  private App context;
  
  public void setContext(App context) {
    this.context = context;
    Student s = ((Student) context.getActiveUser());
    
    ObservableList<MyCourse> courses = FXCollections.observableArrayList(s.getMyCourses());
    FilteredList<MyCourse> filteredCourses = new FilteredList<>(courses);
    
    tblMyCourses.setItems(filteredCourses);
    
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
    colCode.setCellValueFactory(new Callback<CellDataFeatures<MyCourse, String>, ObservableValue<String>>() {
      @Override
      public ObservableValue<String> call(CellDataFeatures<MyCourse, String> c) {
        if (c.getValue() != null && c.getValue().getCourse() != null) {
          return new SimpleStringProperty(c.getValue().getCourse().getCode());
        }
        
        return new SimpleStringProperty(".");
      }
    });
    
    // Title column
    colTitle.setCellValueFactory(new Callback<CellDataFeatures<MyCourse, String>, ObservableValue<String>>() {
      @Override
      public ObservableValue<String> call(CellDataFeatures<MyCourse, String> c) {
        if (c.getValue() != null && c.getValue().getCourse() != null) {
          return new SimpleStringProperty(c.getValue().getCourse().getTitle());
        }
        
        return new SimpleStringProperty("");
      }
    });
    
    // Credit column
    colCredit.setCellValueFactory(new Callback<CellDataFeatures<MyCourse, Integer>, ObservableValue<Integer>>() {
      @Override
      public ObservableValue<Integer> call(CellDataFeatures<MyCourse, Integer> c) {
        if (c.getValue() != null && c.getValue().getCourse() != null) {
          return new ReadOnlyObjectWrapper<>(c.getValue().getCourse().getCredit());
        }
        
        return new ReadOnlyObjectWrapper<>(0);
      }
    });
    
    // Days column
    colDays.setCellValueFactory(new Callback<CellDataFeatures<MyCourse, String>, ObservableValue<String>>() {
      @Override
      public ObservableValue<String> call(CellDataFeatures<MyCourse, String> c) {
        if (c.getValue() != null && c.getValue().getCourse() != null) {
          return new SimpleStringProperty(c.getValue().getCourse().getDays());
        }
        
        return new SimpleStringProperty("");
      }
    });
    
    // Start Time column
    colStart.setCellValueFactory(new Callback<CellDataFeatures<MyCourse, String>, ObservableValue<String>>() {
      @Override
      public ObservableValue<String> call(CellDataFeatures<MyCourse, String> c) {
        if (c.getValue() != null && c.getValue().getCourse() != null) {
          return new SimpleStringProperty(c.getValue().getCourse().getStartTime());
        }
        
        return new SimpleStringProperty("");
      }
    });
    
    // End Time column
    colEnd.setCellValueFactory(new Callback<CellDataFeatures<MyCourse, String>, ObservableValue<String>>() {
      @Override
      public ObservableValue<String> call(CellDataFeatures<MyCourse, String> c) {
        if (c.getValue() != null && c.getValue().getCourse() != null) {
          return new SimpleStringProperty(c.getValue().getCourse().getEndTime());
        }
        
        return new SimpleStringProperty("");
      }
    });
    
    // Status column
    colStatus.setCellValueFactory(new Callback<CellDataFeatures<MyCourse, Status>, ObservableValue<Status>>() {
      @Override
      public ObservableValue<Status> call(CellDataFeatures<MyCourse, Status> c) {
        if (c.getValue() != null && c.getValue().getCourse() != null) {
          return new ReadOnlyObjectWrapper<Status>(c.getValue().getStatus());
        }
        
        return new ReadOnlyObjectWrapper<Status>();
      }
    });
    
    // Grade column
    colGrade.setCellValueFactory(new Callback<CellDataFeatures<MyCourse, Grade>, ObservableValue<Grade>>() {
      @Override
      public ObservableValue<Grade> call(CellDataFeatures<MyCourse, Grade> c) {
        if (c.getValue() != null && c.getValue().getCourse() != null) {
          return new ReadOnlyObjectWrapper<Grade>(c.getValue().getGrade());
        }
        
        return new ReadOnlyObjectWrapper<Grade>();
      }
    });
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    
  }
}

package application.controllers;

import java.net.URL;
import java.util.EnumSet;
import java.util.ResourceBundle;
import application.App;
import application.models.*;
import application.services.UserManager;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.util.Callback;

public class AdminControlPanelController implements Initializable {
  @FXML private TableView<Person> tblStudents, tblStudentInfo;
  @FXML private TableView<MyCourse> tblCourses;
  @FXML private TableColumn<Person, String> colUsername;
  @FXML private TableColumn<MyCourse, String> colCode;
  @FXML private TableColumn<MyCourse, String> colTitle;
  @FXML private TableColumn<MyCourse, Grade> colGrade;
  @FXML private TableColumn<MyCourse, Void> colRemove;
  @FXML private TextField txtSearch;
  @FXML private TextField txtUsername, txtPassword;
  @FXML private ComboBox<String> cbMajor;
  @FXML private Button btnSave;
  
  private App context;
  private UserManager userManager;
  private Student selectedStudent;
  private ObservableList<Person> users;
  
  
  public void setContext(App context) {
    this.context = context;
    cbMajor.setItems(FXCollections.observableArrayList("Computer Science", "EE", "Math"));
    userManager = context.getUserManager();
    users = FXCollections.observableArrayList(userManager.getUsers());
    users = users.filtered(u -> u instanceof Student);
    
    // search feature, display all by default
    FilteredList<Person> filteredUsers = new FilteredList<>(users, u -> true);
    txtSearch.textProperty().addListener(observable -> {
      String searchTerm = txtSearch.getText().toUpperCase();

      if (searchTerm.isEmpty()) {
        filteredUsers.setPredicate(u -> true);
      }
      else {
        filteredUsers.setPredicate(u -> u.getUsername().toUpperCase().contains(searchTerm));
      }
    });
    tblStudents.setItems(filteredUsers);
    
    // Username column of User table
    colUsername.setCellValueFactory(u -> new SimpleStringProperty(u.getValue().getUsername()));
    
    // attach row selection event to the student table
    tblStudents.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
      if (newSelection != null) {
        ObservableList<MyCourse> courses = FXCollections.observableArrayList(((Student) newSelection).getMyCourses());
        selectedStudent = (Student) newSelection;
        
        // populate student's data
        txtUsername.setText(selectedStudent.getUsername());
        txtPassword.setText(selectedStudent.getPassword());
        cbMajor.getSelectionModel().select(selectedStudent.getMajor());
        
        // Course Code column
        colCode.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCourse().getCode()));
        
        // Title column
        colTitle.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCourse().getTitle()));
        
        // Grade Column *** COMBOBOX ***
        ObservableList<Grade> grades = FXCollections.observableArrayList(Grade.class.getEnumConstants());
        
        //colGrade.setCellFactory(ComboBoxTableCell.forTableColumn(grades));
        colGrade.setCellValueFactory(new Callback<CellDataFeatures<MyCourse, Grade>, ObservableValue<Grade>>() {
          
          @Override
          public ObservableValue<Grade> call(CellDataFeatures<MyCourse, Grade> param) {
              MyCourse course = param.getValue();
              return new SimpleObjectProperty<Grade>(course.getGrade());
          }
        });

        colGrade.setCellFactory(ComboBoxTableCell.forTableColumn(grades));

        colGrade.setOnEditCommit((CellEditEvent<MyCourse, Grade> event) -> {
          TablePosition<MyCourse, Grade> pos = event.getTablePosition();

          Grade newGrade = event.getNewValue();

          int row = pos.getRow();
          MyCourse course = event.getTableView().getItems().get(row);

          course.setGrade(newGrade);
        });
        
//        colGrade.setCellValueFactory(new Callback<CellDataFeatures<MyCourse, Grade>, ObservableValue<Grade>>() {
//          @Override
//          public ObservableValue<Grade> call(CellDataFeatures<MyCourse, Grade> c) {
//            if (c.getValue() != null && c.getValue().getCourse() != null) {
//              return new ReadOnlyObjectWrapper<>(c.getValue().getGrade());
//            }
//            
//            return new ReadOnlyObjectWrapper<>(null);
//          }
//        });
        
        
        // Delete column
        colRemove.setCellFactory(param -> new TableCell<MyCourse, Void>() {
          private Hyperlink actionLink = new Hyperlink("remove");
          {
           // remove course from student event
            actionLink.setOnAction(e -> {
              MyCourse c = getTableView().getItems().get(getIndex());
              selectedStudent.unenrollFromCourse(c.getCourse().getID());
              courses.remove(c);
            });
          }
          @Override
          protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : actionLink);
          }
        });
        
        // set data for courses table
        tblCourses.setItems(courses);
      }
    });
  }
  
  public void onBackLinkClick() {
    context.navigateToCourseBrowser();
  }
  
  public void onApplyChangesButtonClick() {
    String oldUsername = selectedStudent.getUsername();
    selectedStudent.setUsername(txtUsername.getText());
    selectedStudent.setPassword(txtPassword.getText());
    selectedStudent.setMajor(cbMajor.getValue());
    // have to delete and re-add, in order to update the new username key in collection
    context.getUserManager().removeUser(oldUsername);
    context.getUserManager().addUser(selectedStudent);
    tblStudents.refresh();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    
  }
}

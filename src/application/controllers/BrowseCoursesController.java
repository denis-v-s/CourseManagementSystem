package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.App;
import application.models.*;
import javafx.collections.*;
import javafx.collections.transformation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

public class BrowseCoursesController implements Initializable {
  @FXML private ListView<Course>  courseListView;
  @FXML private MenuItem btnAddCourse, btnMyCourses, btnStudents, btnLogout;
  @FXML private TextField txtSearch;
    
  private App context;
  
  public void setContext(App context) {
    this.context = context;
    
    // available courses
    ObservableList<Course> items = FXCollections.observableArrayList(
      context.getCourseManager().getCourses()
    );
    
    // search feature, display all by default
    FilteredList<Course> filteredItems = new FilteredList<>(items, c -> true);
    txtSearch.textProperty().addListener(observable -> {
      String searchTerm = txtSearch.getText().toUpperCase();

      if (searchTerm.isEmpty()) {
        filteredItems.setPredicate(c -> true);
      }
      else {
        filteredItems.setPredicate(c -> c.getTitle().toUpperCase().contains(searchTerm));
        courseListView.refresh();
      }
    });
    
    courseListView.setItems(filteredItems);
    courseListView.setCellFactory(new CourseCellItemFactory());
    
    // set button visibility based on who is logged in
    btnAddCourse.setVisible(isAdmin());
    btnMyCourses.setVisible(!isAdmin());
    btnStudents.setVisible(isAdmin());
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    
  }
  
  public void onAddCourseButtonClick() {
    context.navigateToEditCourse();
  }
  
  public void onMyCoursesButtonClick() {
    context.navigateToMyCourses();
  }
  
  public void onRegisteredStudentsButtonClick() {
    context.navigateToAdminControlPanel();
  }
  
  public void onStudentListButtonClick() {
    context.navigateToAdminControlPanel();
  }
  
  public void onLogoutButtonClick() {
    context.setActiveUser(null);
    context.navigateToLogin();
  }
  
  private boolean isAdmin() {
    return context.getActiveUser() instanceof Admin;
  }
  
  // inner class for cell factory
  class CourseCellItemFactory implements Callback<ListView<Course>, ListCell<Course>> {
    @Override
    public ListCell<Course> call(ListView<Course> param) {
      return new CourseCellController(context);
    }
  }
}

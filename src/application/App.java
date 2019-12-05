package application;
	
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import application.controllers.*;
import application.models.*;
import application.services.CourseManager;
import application.services.UserManager;
import application.services.Utils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;

public class App extends Application {
  private UserManager userManager;
  private CourseManager courseManager = new CourseManager();
  private Person activeUser;
  private Course selectedCourse;
  private BorderPane root = new BorderPane();
  
	@Override
	public void start(Stage primaryStage) {
		try {
		  String usersFileName = "users.dat";
		  String coursesFileName = "courses.dat";
		  // set up users
		  if (Files.exists(Paths.get(usersFileName))) {
		    userManager = Utils.<UserManager>loadData(usersFileName);
		  }
		  else {
		    userManager = new UserManager();
		  }
		  
		  // set up courses
		  if (Files.exists(Paths.get(coursesFileName))) {
        courseManager = Utils.<CourseManager>loadData(coursesFileName);       
      }
      else {
        courseManager = new CourseManager();
      }
		  
			Scene scene = new Scene(navigateToLogin());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			// auto-save on application exit
			primaryStage.setOnCloseRequest(e -> {
			  e.consume();
			  Utils.saveData(userManager, courseManager);
			  Platform.exit();
			});
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	public UserManager getUserManager() {
	  return this.userManager;
	}
	
	public CourseManager getCourseManager() {
	  return this.courseManager;
	}
	
	public Person getActiveUser() {
	  return this.activeUser;
	}
	
	public void setActiveUser(Person user) {
	  this.activeUser = user;
	}
	
	public Course getSelectedCourse() {
	  return this.selectedCourse;
	}
	
	public void setSelectedCourse(Course course) {
	  this.selectedCourse = course;
	}
	
	// navigation methods
	public Parent navigateToLogin() {
    try {
      LoginPageController controller = (LoginPageController) setScene("./views/LoginView.fxml");
      controller.setContext(this);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    return root;
  }
	
	public Parent navigateToRegistration() {
    try {
      RegisterPageController controller = (RegisterPageController) setScene("./views/RegisterView.fxml");
      controller.setContext(this);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    return root;
  }
	
	public Parent navigateToCourseBrowser() {
    try {
      BrowseCoursesController controller = (BrowseCoursesController) setScene("./views/BrowseCoursesView.fxml");
      controller.setContext(this);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    return root;
  }
	
	public Parent navigateToMyCourses() {
    try {
      MyCoursesController controller = (MyCoursesController) setScene("./views/MyCoursesView.fxml");
      controller.setContext(this);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    return root;
  }
	
	public Parent navigateToEditCourse() {
    try {
      AddEditCourseController controller = (AddEditCourseController) setScene("./views/AddEditCourseView.fxml");
      controller.setContext(this);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    return root;
  }
	
	public Parent navigateToAdminControlPanel() {
    try {
      AdminControlPanelController controller = (AdminControlPanelController) setScene("./views/AdminControlPanelView.fxml");
      controller.setContext(this);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    return root;
  }
	// end navigation methods
	
	public Initializable setScene(String page) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    loader.setBuilderFactory(new JavaFXBuilderFactory());
    loader.setLocation(getClass().getResource(page));
    InputStream in = getClass().getResourceAsStream(page);
    
    Parent node;
    
    try {
      node = (Parent) loader.load(in);
    }
    finally {
      in.close();
    }
    root.setCenter(node);
    
    return (Initializable) loader.getController();
  }
	
	public static void main(String[] args) {
		launch(args);
	}
}

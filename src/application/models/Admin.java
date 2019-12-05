package application.models;

import application.services.CourseManager;

public class Admin extends Person {
  private static final long serialVersionUID = 1L;
  private CourseManager courseManager;
  
  public Admin(String name, String address, int age, String phoneNumber, String gender) {
    super(name, address, age, phoneNumber, gender);
  }
  
  public void setCourseManager(CourseManager courseManager) {
    this.courseManager = courseManager;
  }
  
  public void addCourse(String courseName, int credit, String textbook) {
    //courseManager.addCourse(courseName, credit, textbook);
  }
  
  public void removeCourse(int courseId) {
    courseManager.removeCourse(courseId);
  }
  
  public void editCourse(int courseId) {
    //courseManager.editCourse(courseId);
  }
}
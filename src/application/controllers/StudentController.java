package application.controllers;

import application.models.Course;
import application.models.Student;

public class StudentController {
  Student studentModel;
  
  public StudentController() {
    // this.studentModel = new Student();
  }
  
  public void enrollToCourse(Course course) {
    studentModel.enrollToCourse(course);
  }

  public void unenrollFromCourse(int courseID) {
    studentModel.unenrollFromCourse(courseID);
  }
}

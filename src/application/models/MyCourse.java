package application.models;

import java.io.Serializable;

import javafx.beans.property.*;

public class MyCourse implements Serializable {
  private static final long serialVersionUID = 1L;
  private Course course;
  private Grade grade;
  private Status status;

  public MyCourse(Course course, Grade grade, Status status) {
    this.course = course;
    this.grade = grade;
    this.status = status;
  }
  
  public Course getCourse() {
    return course;
  }
  
  public void setCourse(Course course) {
    this.course = course;
  }

  public Grade getGrade() {
    return this.grade;
  }

  public void setGrade(Grade grade) {
    this.grade = grade;
  }

  public Status getStatus() {
    return status;
  }

  // IN_PROGRESS, PASSED, FAILED, WITHDREW
  public void setStatus(Status status) {
    this.status = status;
  }
}
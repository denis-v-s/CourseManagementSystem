package application.models;

import java.io.Serializable;
import java.util.*;

public class Student extends Person {
  private static final long serialVersionUID = 1L;
  private String major;
  private HashMap<Integer, MyCourse> courses = new HashMap<>();
  
  public Student() {
    super();
  }

  public double getGPA() {
    double sum = 0;
    
    // compute the sum of grade points, and return the average
    for (MyCourse c : courses.values()) {
      sum += c.getGrade().getGradePoints();
    }

    return sum / courses.size();
  }
  
  public ArrayList<MyCourse> getMyCourses() {
    return new ArrayList<MyCourse>(courses.values());
  }

  public String getMajor() {
    return this.major;
  }

  public void setMajor(String major) {
    this.major = major;
  }
  
  public void enrollToCourse(Course course) {
    courses.put(course.getID(), new MyCourse(course, null, Status.IN_PROGRESS));
  }

  public void unenrollFromCourse(int courseID) {
    courses.remove(courseID);
  }
  
  public boolean isEnrolled(int courseID) {
    return courses.containsKey(courseID);
  }
  
  public void setGrade(int courseID, Grade grade) {
    courses.get(courseID).setGrade(grade);
  }
  
  public Grade getGrade(int courseID) {
    return courses.get(courseID).getGrade();
  }

  @Override
  public String toString() {
    return String.format("%s, Student ID: %d, Major: %s, Avg GPA: %.2f", 
      super.toString(), this.getID(), this.getMajor(), this.getGPA()
    );
  }

//  @Override
//  public boolean equals(Object o) {
//    Student s = (Student) o;
//    return (super.equals(s) && this.getMajor().equals(s.getMajor()));
//  }
}

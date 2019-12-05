package application.services;

import java.io.Serializable;
import java.util.*;

import application.models.Course;

public class CourseManager implements Serializable {
  private static final long serialVersionUID = 1L;
  private HashMap<Integer, Course> courses = new HashMap<>();
  
  public ArrayList<Course> getCourses() {
    return new ArrayList<Course>(courses.values());
  }
  
  public CourseManager() {
    if (courses.isEmpty()) {
      setUpMockCourses();
    }
  }
  
  public void addCourse(Course course) {
    courses.put(course.getID(), course);
  }
      
  public void removeCourse(int courseId) {
    courses.remove(courseId);
  }
  
  public Course getCourse(int courseId) {
    return courses.get(courseId);
  }
  
  public void editCourse(int courseId, Course course) {
    courses.get(courseId).edit(course);
  }
  
  public boolean courseExists(int courseId) {
    return courses.containsKey(courseId);
  }
  
//mock data
 private void setUpMockCourses() {
   Course course = new Course();
   course.setCode("CSE 118");
   course.setTitle("Fundamentals of Programming");
   course.setCredit(4);
   course.setDays("MW");
   course.setStartTime("11:00 AM");
   course.setEndTime("12:00 PM");
   course.setInstructor("Qinghai Gao");
   course.setPrereq("MAT111 or higher");
   course.setDescription("An introductory programming course for the Computer Science major. Topics include basic computer and programming concepts such as hardware, software, numbering systems, identifiers, variables, constants, data types, and operations, standard input and output, selections, loops, functions and methods, single and multidimensional arrays, and objects and classes. The course consists of 100-minute face-to-face lecture and 100-minute instructor-led lab each week for 15 weeks. Weekly homework programming projects and a final project of at least 100 lines of source code are expected. (2 hrs. lecture, 2 hrs. laboratory)");
   
   addCourse(course);
   
   course = new Course();
   course.setCode("CSE 148");
   course.setTitle("Object-Oriented Programming");
   course.setCredit(4);
   course.setDays("TR");
   course.setStartTime("11:00 AM");
   course.setEndTime("12:00 PM");
   course.setInstructor("Bin Li");
   course.setPrereq("CSE118 with a C or higher");
   course.setDescription("An intermediate programming course for the Computer Science major. Topics include class abstraction and encapsulation, inheritance and polymorphism, exception handling and text IO, abstract classes and interfaces, graphical user interface, event-driven programming, binary I/0, and recursion. The course consists of 200-minute face-to-face lecture with some instructor-led lab practice each week. Weekly programming homework projects and a final project of at least 500 lines of source code are required.");
   
   addCourse(course);
   
   course = new Course();
   course.setCode("CSE 218");
   course.setTitle("Data Structures and Algorithms");
   course.setCredit(3);
   course.setDays("MW");
   course.setStartTime("9:00 AM");
   course.setEndTime("10:30 AM");
   course.setInstructor("Xingbin Chen");
   course.setPrereq("CSE148 with a C or higher");
   course.setDescription("An extension of programming methodology to cover data storage and manipulation on complex data sets. Topics include: programming and applications of data structures; stacks, queues, lists, binary trees, heaps, priority queues, balanced trees and graphs. Recursive programming is heavily utilized. Fundamental sorting and searching algorithms are examined along with informal efficiency comparisons. Students expected to be proficient with a professional IDE for coding and debugging. The course consists of 100-minute face-to-face lecture and 100-minute instructor-led lab each week. (2 hrs. lecture, 2 hrs. laboratory)");
   
   addCourse(course);
 }
}

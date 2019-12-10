package application.models;

import java.io.Serializable;

public class Course implements Cloneable, Serializable {
  private static final long serialVersionUID = 1L;
  private String title;
  private int id;
  private int credit;
  private String textbook;
  private String code; // course code
  private String days; // days when the course takes place
  private String instructor; // course's instructor
  private String description; // long description
  private String prereq; // prerequisites
  private String startTime;
  private String endTime;

  // constructors
  public Course() {
    super();
    this.id = this.hashCode();
  }

  public Course(Course c) {
    this();
    edit(c);
  }
  
  public void edit(Course c) {
    this.setTitle(c.getTitle());
    this.setCredit(c.getCredit());
    this.setTextbook(c.getTextbook());
    this.setCode(c.getCode());
    this.setDays(c.getDays());
    this.setInstructor(c.getInstructor());
    this.setPrereq(c.getPrereq());
    this.setStartTime(c.getStartTime());
    this.setEndTime(c.getEndTime());
  }

  public int getID() {
    return this.id;
  }
  
  public String getTitle() {
    return this.title;
  }

  public void setTitle(String courseName) {
    this.title = courseName;
  }

  public int getCredit() {
    return this.credit;
  }

  public void setCredit(int credit) {
    this.credit = credit;
  }
  
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDays() {
    return days;
  }

  public void setDays(String days) {
    this.days = days;
  }

  public void setStartTime(String time) {
    this.startTime = time;
  }
  
  public String getStartTime() {
    return this.startTime;
  }
  
  public void setEndTime(String time) {
    this.endTime = time;
  }
  
  public String getEndTime() {
    return this.endTime;
  }
  
  public String getTime() {
    return this.startTime + " - " + this.endTime;
  }

  public String getInstructor() {
    return instructor;
  }

  public void setInstructor(String instructor) {
    this.instructor = instructor;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPrereq() {
    return prereq;
  }

  public void setPrereq(String prereq) {
    this.prereq = prereq;
  }

  public String getTextbook() {
    return this.textbook;
  }

  public void setTextbook(String textbook) {
    this.textbook = textbook;
  }
  
  @Override
  public Course clone() throws CloneNotSupportedException {
    return (Course) super.clone();
  }
  
  @Override
  public boolean equals(Object o) {
    if (o == null) return false;
    
    Course c = (Course) o;
    return this.getCode().equals(c.getCode());
  }
}

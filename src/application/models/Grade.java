package application.models;

public enum Grade {
  A (5),
  B (4),
  C (3),
  D (2),
  F (1);
  
  int gradePoints;
  
  Grade(int grade) {
    gradePoints = grade;
  }
  
  public int getGradePoints() {
    return gradePoints;
  }
}

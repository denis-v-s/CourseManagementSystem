package application.models;

public class Admin extends Person {
  private static final long serialVersionUID = 1L;
  
  public Admin(String name, String address, int age, String phoneNumber, String gender) {
    super(name, address, age, phoneNumber, gender);
  }
}
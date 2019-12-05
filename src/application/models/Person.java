package application.models;

import java.io.Serializable;

public class Person implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String address;
  private int age;
  private String phoneNumber;
  private String emailAddress;
  private String gender;
  private String username;
  private String password;
  private int id = 0;
  
  public Person() {
    this.id = this.hashCode();
  }

  public Person(String name, String address, int age, String phoneNumber, String gender) {
    this();
    this.setName(name);
    this.setAddress(address);
    this.setAge(age);
    this.setPhoneNumber(phoneNumber);
    this.setGender(gender);
  }
  
  public int getID() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }

  // getters / setters for name, address, age, and phone
  public String getAddress() {
    return this.address;
  }
  public void setAddress(String address) {
    this.address = address;
  }

  public int getAge() {
    return this.age;
  }
  public void setAge(int age) {
    this.age = age;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  
  public String getEmailAddress() {
    return this.emailAddress;
  }
  
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }
  
  public String getGender() {
    return this.gender;
  }
  public void setGender(String gender) {
    this.gender = gender;
  }
  
  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return String.format(
        "My name is %S, I'm %d years old, %s",
        this.getName(),
        this.getAge(),
        this.getGender()
    );
  }
  
  @Override
  public boolean equals(Object o) {
    if (o == null) return false;
      
    Person p = (Person) o;
    return this.getUsername().equals(p.getUsername());      
  }
}

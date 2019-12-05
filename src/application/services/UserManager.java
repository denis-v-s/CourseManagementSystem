package application.services;

import java.io.Serializable;
import java.util.*;

import application.models.Admin;
import application.models.Person;
import application.models.Student;

public class UserManager implements Serializable {
  private static final long serialVersionUID = 1L;
  // hashmap with username as key, and user object as value
  private HashMap<String, Person> users = new HashMap<>();
  
  public UserManager() {
    if (users.isEmpty()) {
      setUpMockUsers();
    }
  }
  
  public void addUser(Person user) {
    users.put(user.getUsername(), user);
  }
  
  public void removeUser(String username) {
    users.remove(username);
  }
  
  public boolean userExists(String username) {
    return users.containsKey(username);
  }
  
  public Person getUser(String username) {
    return users.get(username);
  }
  
  public ArrayList<Person> getUsers() {
    return new ArrayList<Person>(users.values());
  }
  
  public boolean assertLogin(String username, String password) {
    return users.containsKey(username) && users.get(username).getPassword().equals(password);
  }
  
  // TODO
  public void generateReport() {
    throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  
  // mock data
  private void setUpMockUsers() {
    Admin admin = new Admin("John", null, 35, null, "Male");
    admin.setUsername("admin");
    admin.setPassword("cse148");
    
    addUser(admin);
    
    Student s = new Student();
    s.setUsername("a");
    s.setPassword("a");
    s.setMajor("Computer Science");
    addUser(s);
    
    s = new Student();
    s.setUsername("IronMan");
    s.setPassword("secret");
    s.setMajor("Underwater Basket Weaving");
    addUser(s);
    
    s = new Student();
    s.setUsername("BruceLee");
    s.setPassword("a");
    s.setMajor("Martial Arts");
    addUser(s);
  }
}
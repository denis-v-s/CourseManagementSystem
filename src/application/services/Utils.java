package application.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Utils {
  public static void saveData(UserManager users, CourseManager courses) {
    try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
      output.writeObject(users);
    }
    catch (Exception e) {
      System.out.println("unable to write users to file");
      e.printStackTrace();
    }
    
    try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("courses.dat"))) {
      output.writeObject(courses);
    }
    catch (Exception e) {
      System.out.println("unable to write courses to file");
      e.printStackTrace();
    }
  }
  
  public static <T> T loadData(String fileName) {
    T data = null;
    try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))) {
      data = (T) input.readObject();
    }
    catch(Exception e) {
      System.out.println("unable to read save.dat");
      e.printStackTrace();
    }
    
    return data;
  }
}

package generics;//: generics/InstantiateGenericType.java
import static net.mindview.util.Print.*;

class ClassAsFactory<T> {
  T x;
  public ClassAsFactory(Class<T> kind) {
    try {
      x = kind.newInstance();
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
  }
}

class Employee1 {}	

public class InstantiateGenericType {
  public static void main(String[] args) {
    ClassAsFactory<Employee1> fe =
      new ClassAsFactory<Employee1>(Employee1.class);
    print("ClassAsFactory<Employee1> succeeded");
    try {
      ClassAsFactory<Integer> fi =
        new ClassAsFactory<Integer>(Integer.class);
    } catch(Exception e) {
      print("ClassAsFactory<Integer> failed");
    }
  }
} /* Output:
ClassAsFactory<Employee1> succeeded
ClassAsFactory<Integer> failed
*///:~

package comparingstudents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author TheMy
 */
public class Datasource {
    static int[] znamky_Alice = {2, 3, 2, 1, 1};
    static int[] znamky_Bob = {3, 4, 4};
    static int[] znamky_Cyril = {2, 5, 2, 5, 1};
    private static Student[] data = {
      new Student("Alice","Mala",345, znamky_Alice),
      new Student("Bob","Velky",123, znamky_Bob),
      new Student("Cyril","Stredny",567, znamky_Cyril)
    };
    
    public static Student[] loadDataAsArray(){
        return Arrays.copyOf(data, data.length);
    }
    
    public static List<Student> loadDataAsList(){
        return Arrays.asList(data);
//        ArrayList<Student> students = new ArrayList<>();
//        students.addAll(Arrays.asList(data));
    }
}

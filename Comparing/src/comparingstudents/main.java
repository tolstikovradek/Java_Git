package comparingstudents;

import static comparingstudents.MyComparing.print;
import static comparingstudents.MyComparing.sort;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author TheMy
 */
public class main {

    public static void main(String[] args) {
        Student[] students = Datasource.loadDataAsArray();
        print(students);
        sort(students, new PorovnavacStudentByAverage());
        print(students);

    }
}

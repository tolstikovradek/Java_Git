package comparingstudents;

/**
 *
 * @author TheMy
 */
public class PorovnavacStudentByNumber implements ComparatorInterface{

    @Override
    public boolean bigger(Object o1, Object o2) {
       return ((Student)o1).getStudentNumber() > ((Student)o2).getStudentNumber();
    }
    
    
}

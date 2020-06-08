package comparingstudents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author TheMy
 */
public class Student implements CompareInterface, Comparable {
    //data
    private String firstName;
    private String lastName;
    private int studentNumber;
    private ArrayList<Integer> gardes;
    private double averageGrade;

    //konstruktor
    public Student(String firstName, String lastName, int studentNumber) {
        this.gardes = new ArrayList(0);
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        countAverage();
    }
    
    public Student(String firstName, String lastName, int studentNumber, ArrayList grades) {
        this.gardes = grades;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        countAverage();
    }
    
    public Student(String firstName, String lastName, int studentNumber, int[] newGrades) {
        this.gardes = new ArrayList(newGrades.length);
        for (int i = 0; i < newGrades.length; i++) {
            gardes.add(i, newGrades[i]);
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        countAverage();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public double getAverageGrade() {
        return averageGrade;
    }
    

    
    @Override
    public String toString() {
        return String.format("%10s %10s %5d %.2f %20s", firstName, lastName, studentNumber, averageGrade, this.printGrades());
    }

    public boolean isBigger(Student student) {
        return this.studentNumber > student.studentNumber;
    }

    @Override
    public boolean isBigger(CompareInterface o) {
        return this.studentNumber > ((Student)o).studentNumber;
    }

    @Override
    public int compareTo(Object o) {
        return this.studentNumber - ((Student)o).studentNumber;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.firstName);
        hash = 29 * hash + Objects.hashCode(this.lastName);
        hash = 29 * hash + this.studentNumber;
        return hash;
    }
    
    private void countAverage(){
        double sumGrade = 0;
        double averageGrade;
        for (int grade : this.gardes) {
            sumGrade += grade;
        }
        this.averageGrade = sumGrade/gardes.size();
    }
    
    private void addGrade(int grade){
        this.gardes.add(grade);
        countAverage();
    }
    
    private void changeGrade(int grade, int position){
        this.gardes.add(position, grade);
        countAverage();
    }
    
    private String printGrades(){
        String gradesString = "[";
        for (int i = 0; i < this.gardes.size()-1; i++) {
            gradesString += gardes.get(i);
            gradesString += ", ";
        }
        gradesString += gardes.get(gardes.size()-1);
        gradesString += "]";
        return gradesString;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.studentNumber != other.studentNumber) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return true;
    }
    
}

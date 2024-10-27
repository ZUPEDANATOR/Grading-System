package gradingsystem;

import java.util.Arrays;

public class Student {

    private String studentID;
    private String firstName;
    private String lastName;
    private double[] grades;
    private double averageGrade;

    // Constructor
    public Student(String studentID, String firstName, String lastName, int numSubjects) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grades = new double[numSubjects]; // Initialize grades array
    }

    // Getter and Setter methods
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double[] getGrades() {
        return grades;
    }

    public void setGrades(double[] grades) {
        this.grades = grades;
        calculateAverageGrade();
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    // Method to calculate the average grade
    public void calculateAverageGrade() {
        double total = 0;
        for (double grade : grades) {
            total += grade;
        }
        this.averageGrade = grades.length > 0 ? total / grades.length : 0;
    }

    // Method to get the grades as a string for display
    public String gradesToString() {
        StringBuilder gradesStr = new StringBuilder();
        for (int i = 0; i < grades.length; i++) {
            gradesStr.append("Module ").append(i + 1).append(": ").append(grades[i]).append("\n");
        }
        return gradesStr.toString();
    }

    // Method to print the student's details
    @Override
    public String toString() {
        return "ID: " + studentID + ", Name: " + firstName + " " + lastName;
    }
}

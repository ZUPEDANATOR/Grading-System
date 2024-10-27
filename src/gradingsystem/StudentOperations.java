package gradingsystem;

import javax.swing.*;
import java.util.ArrayList;

public class StudentOperations {

    private static ArrayList<Student> students = new ArrayList<>();

public static void addNewStudent() {
    String studentID;
    // Loop to ensure valid student ID input
    do {
        studentID = JOptionPane.showInputDialog("Enter student ID (9 digits):");
        // Check for Cancel or invalid format
        if (studentID == null || !studentID.matches("\\d{9}")) {
            JOptionPane.showMessageDialog(null, "Student ID must be exactly 9 digits. Try again.");
        }
    } while (studentID == null || !studentID.matches("\\d{9}"));

    String firstName;
    // Loop to ensure valid first name input
    do {
        firstName = JOptionPane.showInputDialog("Enter first name (no numbers):");
        // Check for Cancel or invalid format
        if (firstName == null || !firstName.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "First name cannot contain numbers. Try again.");
        }
    } while (firstName == null || !firstName.matches("[a-zA-Z]+"));

    String lastName;
    // Loop to ensure valid last name input
    do {
        lastName = JOptionPane.showInputDialog("Enter last name (no numbers):");
        // Check for Cancel or invalid format
        if (lastName == null || !lastName.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Last name cannot contain numbers. Try again.");
        }
    } while (lastName == null || !lastName.matches("[a-zA-Z]+"));

    int numSubjects;
    // Validate number of subjects input
    while (true) {
        String numSubjectsInput = JOptionPane.showInputDialog("Enter number of subjects:");
        if (numSubjectsInput == null) {
            return; // Exit if Cancel is pressed
        }
        try {
            numSubjects = Integer.parseInt(numSubjectsInput);
            if (numSubjects > 0) break; // Ensure it's a positive number
            else JOptionPane.showMessageDialog(null, "Number of subjects must be positive. Try again.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
        }
    }

    // Create a new Student object and add it to the list
    Student student = new Student(studentID, firstName, lastName, numSubjects);
    students.add(student);
    JOptionPane.showMessageDialog(null, "Student added successfully!");
}

public static void enterGrades() {
    String studentID = JOptionPane.showInputDialog("Enter student ID to enter grades:");
    Student student = findStudent(studentID);

    if (student != null) {
        double[] grades = new double[student.getGrades().length];
        for (int i = 0; i < grades.length; i++) {
            String moduleName = ModuleOperations.getModuleName(i);
            String gradeInput = JOptionPane.showInputDialog("Enter grade for module " + moduleName + ":");
            // Validate grade input
            if (gradeInput != null) {
                try {
                    grades[i] = Double.parseDouble(gradeInput);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid grade input. Please enter a numeric value.");
                    i--; // Retry the same index
                }
            } else {
                return; // Exit if Cancel is pressed
            }
        }
        student.setGrades(grades);
        JOptionPane.showMessageDialog(null, "Grades entered successfully!");
    } else {
        JOptionPane.showMessageDialog(null, "Student not found.");
    }
}

    public static void viewAllStudents() {
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No students in the system.");
        } else {
            StringBuilder allStudents = new StringBuilder("Students:\n");
            for (Student student : students) {
                allStudents.append(student).append("\n");
                allStudents.append("Grades:\n").append(student.gradesToString());
                allStudents.append("Average Grade: ").append(String.format("%.2f", student.getAverageGrade())).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, allStudents.toString());
        }
    }

    public static void updateStudentInformation() {
        String studentID = JOptionPane.showInputDialog("Enter student ID to update:");
        Student student = findStudent(studentID);

        if (student != null) {
            String firstName = JOptionPane.showInputDialog("Enter new first name:", student.getFirstName());
            String lastName = JOptionPane.showInputDialog("Enter new last name:", student.getLastName());
            student.setFirstName(firstName);
            student.setLastName(lastName);
            JOptionPane.showMessageDialog(null, "Student information updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Student not found.");
        }
    }

    public static void deleteStudent() {
        String studentID = JOptionPane.showInputDialog("Enter student ID to delete:");
        Student student = findStudent(studentID);

        if (student != null) {
            students.remove(student);
            JOptionPane.showMessageDialog(null, "Student deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Student not found.");
        }
    }

    public static void calculateAndDisplayAverageGrade() {
        String studentID = JOptionPane.showInputDialog("Enter student ID to calculate average grade:");
        Student student = findStudent(studentID);

        if (student != null) {
            student.calculateAverageGrade();
            JOptionPane.showMessageDialog(null, "The average grade for " + student.getFirstName() + " " + student.getLastName()
                    + " is " + String.format("%.2f", student.getAverageGrade()));
        } else {
            JOptionPane.showMessageDialog(null, "Student not found.");
        }
    }

    private static Student findStudent(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }
}

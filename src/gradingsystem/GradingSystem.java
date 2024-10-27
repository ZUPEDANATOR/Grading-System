package gradingsystem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GradingSystem {

    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<String> moduleNames = new ArrayList<>();

    public static void main(String[] args) {
        // Prompt user to choose console or GUI
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to use console or GUI? (type 'console' or 'gui'): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("gui")) {
            SwingUtilities.invokeLater(() -> {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            });
        } else if (choice.equalsIgnoreCase("console")) {
            consoleMode(scanner);
        } else {
            System.out.println("Invalid choice. Exiting program.");
            scanner.close();
            return;
        }
    }

    private static void consoleMode(Scanner scanner) {
        // Console interface logic
        System.out.println("Welcome to the Student Grade Management System - Console Mode");
        boolean running = true;

        while (running) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Add New Student");
            System.out.println("2. Add Module Name");
            System.out.println("3. Enter Grades");
            System.out.println("4. View All Students");
            System.out.println("5. Update Student Information");
            System.out.println("6. Delete Student");
            System.out.println("7. Calculate and Display Average Grade");
            System.out.println("8. Exit");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    addNewStudent(scanner);
                    break;
                case "2":
                    addModuleName(scanner);
                    break;
                case "3":
                    enterGrades(scanner);
                    break;
                case "4":
                    viewAllStudents();
                    break;
                case "5":
                    updateStudentInformation(scanner);
                    break;
                case "6":
                    deleteStudent(scanner);
                    break;
                case "7":
                    calculateAndDisplayAverageGrade();
                    break;
                case "8":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        System.out.println("Exiting console mode.");
        scanner.close(); // Close the scanner when done
    }

    public static void addNewStudent(Scanner scanner) {
        String studentID;
        while (true) {
            System.out.print("Enter Student ID (9 digits): ");
            studentID = scanner.nextLine();
            if (studentID.matches("\\d{9}")) {
                break; // Valid 9-digit ID
            } else {
                System.out.println("Invalid Student ID. Please enter exactly 9 digits.");
            }
        }

        String firstName;
        while (true) {
            System.out.print("Enter First Name (letters only): ");
            firstName = scanner.nextLine();
            if (firstName.matches("[a-zA-Z]+")) {
                break; // Valid first name
            } else {
                System.out.println("Invalid First Name. Please use letters only.");
            }
        }

        String lastName;
        while (true) {
            System.out.print("Enter Last Name (letters only): ");
            lastName = scanner.nextLine();
            if (lastName.matches("[a-zA-Z]+")) {
                break; // Valid last name
            } else {
                System.out.println("Invalid Last Name. Please use letters only.");
            }
        }

        // Assuming a default number of modules for the student
        Student student = new Student(studentID, firstName, lastName, moduleNames.size());
        students.add(student);

        System.out.println("Student added: " + student.toString());
    }

    public static void addModuleName(Scanner scanner) {
        System.out.print("Enter Module Name: ");
        String moduleName = scanner.nextLine();
        moduleNames.add(moduleName);
        System.out.println("Module added: " + moduleName);
    }

    public static void enterGrades(Scanner scanner) {
        System.out.print("Enter Student ID to enter grades: ");
        String studentID = scanner.nextLine();

        // Find the student by ID
        Student student = null;
        for (Student s : students) {
            if (s.getStudentID().equals(studentID)) {
                student = s;
                break;
            }
        }

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        // Enter grades for each module
        double[] grades = new double[moduleNames.size()];
        for (int i = 0; i < moduleNames.size(); i++) {
            System.out.print("Enter grade for module " + moduleNames.get(i) + ": ");
            grades[i] = Double.parseDouble(scanner.nextLine());
        }

        student.setGrades(grades);
        System.out.println("Grades entered for " + student.getFirstName() + " " + student.getLastName());
    }

    public static void viewAllStudents() {
        System.out.println("Viewing all students:");
        for (Student student : students) {
            System.out.print(student.toString());
            System.out.print(", Grades: ");
            for (double grade : student.getGrades()) {
                System.out.print(grade + " ");
            }
            System.out.println(", Average: " + String.format("%.2f", student.getAverageGrade()));
        }
    }

    public static void updateStudentInformation(Scanner scanner) {
        System.out.print("Enter Student ID to update information: ");
        String studentID = scanner.nextLine();

        // Find the student by ID
        Student student = null;
        for (Student s : students) {
            if (s.getStudentID().equals(studentID)) {
                student = s;
                break;
            }
        }

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter new First Name (leave blank to keep unchanged): ");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty() && firstName.matches("[a-zA-Z]+")) {
            student.setFirstName(firstName);
        }

        System.out.print("Enter new Last Name (leave blank to keep unchanged): ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty() && lastName.matches("[a-zA-Z]+")) {
            student.setLastName(lastName);
        }

        System.out.println("Student information updated: " + student.toString());
    }

    public static void deleteStudent(Scanner scanner) {
        System.out.print("Enter Student ID to delete: ");
        String studentID = scanner.nextLine();

        // Find and remove the student by ID
        students.removeIf(s -> s.getStudentID().equals(studentID));
        System.out.println("Student with ID " + studentID + " has been deleted.");
    }

    public static void calculateAndDisplayAverageGrade() {
        System.out.println("Calculating and displaying average grades:");
        for (Student student : students) {
            System.out.println(student.getFirstName() + " " + student.getLastName() + ": Average Grade = " + String.format("%.2f", student.getAverageGrade()));
        }
    }
}

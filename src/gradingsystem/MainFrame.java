package gradingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    private JButton addButton;
    private JButton moduleButton;
    private JButton gradeButton;
    private JButton viewButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton avgGradeButton;
    private JButton exitButton;

    public MainFrame() {
        setTitle("Student Grade Management System");
        setSize(400, 400); // Set the size to fit better on smaller screens
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(8, 1, 5, 5)); // Grid layout for buttons
        add(buttonPanel, BorderLayout.CENTER);

        // Initialize buttons
        addButton = new JButton("Add New Student");
        moduleButton = new JButton("Add Module Name");
        gradeButton = new JButton("Enter Grades");
        viewButton = new JButton("View All Students");
        updateButton = new JButton("Update Student Information");
        deleteButton = new JButton("Delete Student");
        avgGradeButton = new JButton("Calculate and Display Average Grade");
        exitButton = new JButton("Exit");

        // Add buttons to the panel
        buttonPanel.add(addButton);
        buttonPanel.add(moduleButton);
        buttonPanel.add(gradeButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(avgGradeButton);
        buttonPanel.add(exitButton);

        // Set up the action listeners for each button  
        addButton.addActionListener(e -> StudentOperations.addNewStudent());
        moduleButton.addActionListener(e -> ModuleOperations.addModuleName());
        gradeButton.addActionListener(e -> StudentOperations.enterGrades());
        viewButton.addActionListener(e -> StudentOperations.viewAllStudents());
        updateButton.addActionListener(e -> StudentOperations.updateStudentInformation());
        deleteButton.addActionListener(e -> StudentOperations.deleteStudent());
        avgGradeButton.addActionListener(e -> StudentOperations.calculateAndDisplayAverageGrade());

        // Customization for the Exit Button
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Hover effect for exit button
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setBackground(Color.DARK_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setBackground(Color.RED);
            }
        });

        // Adding confirmation and loading animation
        exitButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                    "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                ExitAnimation.showExitWithLoadingAnimation(); // Show loading animation before exit
            }
        });

        // Show the frame in the center of the screen
        setLocationRelativeTo(null);
    }
}

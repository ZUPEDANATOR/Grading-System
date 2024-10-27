package gradingsystem;

import javax.swing.*;
import java.awt.*;

public class ExitAnimation {

    public static void showExitWithLoadingAnimation() {
        // Create a new frame for the loading animation
        JFrame loadingFrame = new JFrame("Exiting...");
        loadingFrame.setSize(250, 100);
        loadingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadingFrame.setLayout(new BorderLayout());

        JLabel loadingLabel = new JLabel("Exiting...", SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 16));
        loadingFrame.add(loadingLabel, BorderLayout.CENTER);

        // Create a progress bar
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        loadingFrame.add(progressBar, BorderLayout.SOUTH);

        loadingFrame.setLocationRelativeTo(null);
        loadingFrame.setVisible(true);

        // Simulate loading with a new thread
        new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i += 20) {
                    progressBar.setValue(i);
                    Thread.sleep(500); // Pause for 500ms for each increment
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            loadingFrame.dispose(); // Close the loading frame
            System.exit(0); // Exit the system after the loading completes
        }).start();
    }
}

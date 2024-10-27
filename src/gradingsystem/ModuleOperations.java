package gradingsystem;

import javax.swing.*;
import java.util.ArrayList;

public class ModuleOperations {

    private static ArrayList<String> moduleNames = new ArrayList<>();

    public static void addModuleName() {
        String moduleName = JOptionPane.showInputDialog("Enter module name:");
        if (moduleName != null && !moduleName.trim().isEmpty()) {
            moduleNames.add(moduleName);
            JOptionPane.showMessageDialog(null, "Module name added successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Module name cannot be empty.");
        }
    }

    public static String getModuleName(int index) {
        if (index < 0 || index >= moduleNames.size()) {
            return "Unknown Module"; // Return a default value if index is out of bounds
        }
        return moduleNames.get(index);
    }

    public static int getModuleCount() {
        return moduleNames.size();
    }
}

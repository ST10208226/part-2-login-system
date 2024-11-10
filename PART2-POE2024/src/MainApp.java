import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        // Simulate user registration and login for testing purposes
        Login userLogin = new Login("user1", "password123", "FirstName", "LastName");

        
        String enteredUsername = JOptionPane.showInputDialog("Enter username:");
        String enteredPassword = JOptionPane.showInputDialog("Enter password:");
        
        if (userLogin.loginUser(enteredUsername, enteredPassword)) {
            JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");
        } else {
            JOptionPane.showMessageDialog(null, "Login failed. Exiting application.");
            System.exit(0);
        }
        
        // Main menu
        List<Task> tasks = new ArrayList<>();
        int totalHours = 0;
        
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks you want to add:"));
        
        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter Task Name:");
            String taskDescription = JOptionPane.showInputDialog("Enter Task Description (50 characters max):");
            if (taskDescription.length() > 50) {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
                i--; // Redo this iteration if description is invalid
                continue;
            }
            String developerDetails = JOptionPane.showInputDialog("Enter Developer Name:");
            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter Task Duration (hours):"));
            String taskStatus = JOptionPane.showInputDialog("Enter Task Status (To Do / Done / Doing):");
            
            Task task = new Task(taskName, i, taskDescription, developerDetails, taskDuration, taskStatus);
            tasks.add(task);
            totalHours += task.getTaskDuration();
            
            JOptionPane.showMessageDialog(null, task.printTaskDetails());
        }
        
        // Show total hours after all tasks are entered
        JOptionPane.showMessageDialog(null, "Total hours for all tasks: " + totalHours);
        
        // Main loop
        while (true) {
            String choice = JOptionPane.showInputDialog("Choose an option:\n1. Add tasks\n2. Show report\n3. Quit");

            if (choice == null || choice.equals("3")) {  // Exit if "Quit" is chosen or dialog is closed
                JOptionPane.showMessageDialog(null, "Thank you for using EasyKanban.");
                break;
            }

            if (choice.equals("1")) {
                JOptionPane.showMessageDialog(null, "Add Task feature");
                // Additional task addition logic if needed
            } else if (choice.equals("2")) {
                JOptionPane.showMessageDialog(null, "Coming Soon");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid option. Please choose 1, 2, or 3.");
            }
        }
    }
}

import javax.swing.*;
import java.util.ArrayList;

class Task {
    int taskNumber;
    String taskName;
    String taskDescription;
    String developerDetails;
    int taskDuration;  // Duration in hours
    String taskStatus;

    // Constructor
    public Task(String taskName, int taskNumber, String taskDescription, String developerDetails, int duration, String taskStatus) {
        this.taskNumber = taskNumber;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = duration;
        this.taskStatus = taskStatus;
    }

    // Method to check task description length
    public boolean checkTaskDescription() {
        return taskDescription.length() <= 50;
    }

    // Method to print task details
    public String printTaskDetails() {
        return "Task Number: " + taskNumber + "\n" +
               "Task Name: " + taskName + "\n" +
               "Task Description: " + taskDescription + "\n" +
               "Developer Details: " + developerDetails + "\n" +
               "Duration: " + taskDuration + " hours\n" +
               "Status: " + taskStatus + "\n" +
               "Task ID: " + createTaskID(); // Call method to generate Task ID
    }

    // Generate Task ID
    public String createTaskID() {
        return taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" + developerDetails.substring(developerDetails.length() - 3).toUpperCase();
    }

    public int getTaskDuration() {
        return taskDuration; // Return the stored duration
    }
}



public class TaskManagementApp {
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        // Login credentials
        String username = JOptionPane.showInputDialog("Enter username to login:");
        String password = JOptionPane.showInputDialog("Enter password to login:");
        
        // Assume a user instance for login
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "First", "Last");
        boolean isLoggedIn = user.loginUser(username, password);
        
        if (isLoggedIn) {
            JOptionPane.showMessageDialog(null, user.returnLoginStatus(true));
            displayMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Login failed. Exiting application.");
        }
    }

    private static void displayMenu() {
        boolean keepRunning = true;

        while (keepRunning) {
            String menuOptions = "Welcome to EasyKanban\n" +
                                 "1) Add Tasks\n" +
                                 "2) Show Report (Coming Soon)\n" +
                                 "3) Quit";
            String choice = JOptionPane.showInputDialog(menuOptions);
            switch (choice) {
                case "1":
                    addTasks();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Coming Soon");
                    break;
                case "3":
                    keepRunning = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please select again.");
            }
        }
    }

    static void addTasks() {
      int numTasks = Integer.parseInt(JOptionPane.showInputDialog("How many tasks do you wish to enter?"));

        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter Task Name:");
            String taskDescription = JOptionPane.showInputDialog("Enter Task Description (max 50 characters):");
            String developerDetails = JOptionPane.showInputDialog("Enter Developer Details (First and Last Name):");
            int taskDuration;

            try {
                taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter Task Duration (in hours):"));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input for duration. Please enter an integer.");
                continue;
            }

            String taskStatus = JOptionPane.showInputDialog("Enter Task Status (To Do, Doing, Done):");

            // Create new task and validate description
            Task task = new Task(taskName, i, taskDescription, developerDetails, taskDuration, taskStatus);


            if (task.checkTaskDescription()) {
                tasks.add(task);
                JOptionPane.showMessageDialog(null, task.printTaskDetails());
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
            }
        }

        // Calculate and show total hours
        int totalHours = returnTotalHours();
        JOptionPane.showMessageDialog(null, "Total hours across all tasks: " + totalHours);
    }

   static int returnTotalHours() {
    int total = 0;
    for (Task task : tasks) {
        total += task.getTaskDuration(); 
    }
    return total;
}

}

import javax.swing.JOptionPane;

class Login {
    String username;
    String password;
    String firstName;
    String lastName;

    // Constructor
    public Login(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Method to check username format
    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    // Method to check password complexity
    public boolean checkPasswordComplexity() {
        return password.length() >= 8 && 
               password.chars().anyMatch(Character::isUpperCase) && 
               password.chars().anyMatch(Character::isDigit) && 
               password.chars().anyMatch(ch -> "!@#$%^&*()_+-=[]{}|;':\",.<>?/`~".indexOf(ch) >= 0);
    }

    // Method to register user
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.";
        }
        return "Registration successful";
    }

    // Method to login user
    public boolean loginUser(String username, String password) {
        // Check if username and password match the registered details
        return this.username.equals(username) && this.password.equals(password);
    }

    // Method to return login status
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + firstName + ", " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Registration and login
        String username = JOptionPane.showInputDialog("Enter username (must contain '_' and max 5 characters):");
        String password = JOptionPane.showInputDialog("Enter password (min 8 characters, at least 1 uppercase, 1 number, 1 special character):");
        String firstName = JOptionPane.showInputDialog("Enter first name:");
        String lastName = JOptionPane.showInputDialog("Enter last name:");

        Login user = new Login(username, password, firstName, lastName);
        String registrationMessage = user.registerUser();
        JOptionPane.showMessageDialog(null, registrationMessage);

        if (!registrationMessage.equals("Registration successful")) {
            return;
        }

        // Login
        String inputUsername = JOptionPane.showInputDialog("Enter username to login:");
        String inputPassword = JOptionPane.showInputDialog("Enter password to login:");
        boolean isLoggedIn = user.loginUser(inputUsername, inputPassword);
        JOptionPane.showMessageDialog(null, user.returnLoginStatus(isLoggedIn));

        if (!isLoggedIn) {
            return;
        }

        // Welcome message and task management
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");

        int totalTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks you want to add:"));
        Task[] tasks = new Task[totalTasks];
        int totalHours = 0;

        for (int i = 0; i < totalTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter task name:");
            String taskDescription = JOptionPane.showInputDialog("Enter task description (max 50 characters):");

            if (taskDescription.length() > 50) {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
                continue;
            }

            String developerDetails = JOptionPane.showInputDialog("Enter developer first and last name:");
            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration in hours:"));
            String taskStatus = JOptionPane.showInputDialog("Select task status (To Do, Done, Doing):");

            Task task = new Task(taskName, i, taskDescription, developerDetails, taskDuration, taskStatus);
            tasks[i] = task;
            totalHours += task.getTaskDuration();

            JOptionPane.showMessageDialog(null, task.printTaskDetails());
        }

        // Total hours display
        JOptionPane.showMessageDialog(null, "Total Task Hours: " + totalHours);

        // Main menu loop
        while (true) {
            String choice = JOptionPane.showInputDialog("Choose an option:\n1. Add tasks\n2. Show report\n3. Quit");

            if (choice == null || choice.equals("3")) {
                JOptionPane.showMessageDialog(null, "Thank you for using the application.");
                break;
            } else if (choice.equals("1")) {
                JOptionPane.showMessageDialog(null, "Task addition complete.");
            } else if (choice.equals("2")) {
                JOptionPane.showMessageDialog(null, "Coming Soon");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid option. Please choose 1, 2, or 3.");
            }
        }
    }
}

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TaskTest {
    private Task task;

    @Before
    public void setUp() {
        // Initialize a Task object before each test
        task = new Task("Sample Task", 1, "This is a sample task description", "John Doe", 5, "To Do");
    }

    @Test
    public void testCheckTaskDescription() {
        // Test that the task description is valid
        assertTrue(task.checkTaskDescription());

        // Set an invalid description and check again
        task = new Task("Sample Task", 1, "This description is way too long to be valid because it exceeds fifty characters.", "John Doe", 5, "To Do");
        assertFalse(task.checkTaskDescription());
    }

    @Test
    public void testPrintTaskDetails() {
        // Test that the printTaskDetails method returns the expected format
        String expectedDetails = "Task Number: 1\n" +
                                 "Task Name: Sample Task\n" +
                                 "Task Description: This is a sample task description\n" +
                                 "Developer Details: John Doe\n" +
                                 "Duration: 5 hours\n" +
                                 "Status: To Do\n" +
                                 "Task ID: SA:1:DOE"; // Adjusted to match expected ID format
        assertEquals(expectedDetails, task.printTaskDetails());
    }

    @Test
    public void testCreateTaskID() {
        // Test the Task ID generation
        String expectedID = "SA:1:DOE"; // Adjusted to match expected ID format
        assertEquals(expectedID, task.createTaskID());
    }

    @Test
    public void testGetTaskDuration() {
        // Test getting the task duration
        assertEquals(5, task.getTaskDuration());
    }
}

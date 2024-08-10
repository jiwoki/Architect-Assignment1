package edu.sdccd.cisc191.template;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AppTest{

    @Test
    public void testTaskCreation() {
        Task task = new Task("Sample Task");
        assertEquals("Sample Task", task.getDescription());
    }

    @Test
    public void testSetDescription() {
        Task task = new Task("Old Description");
        task.setDescription("New Description");
        assertEquals("New Description", task.getDescription());
    }

    private TaskManager taskManager;

    @BeforeEach
    public void setUp() {
        try (FileWriter fileWriter = new FileWriter("ToDo.txt")) {
            fileWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        taskManager = new TaskManager();
    }

    @Test
    public void testAddTask() {
        Task task = new Task("Sample Task");
        taskManager.addTask(task);
        ObservableList<Task> tasks = taskManager.getTasks();
        assertTrue(tasks.contains(task));
    }

    @Test
    public void testRemoveTask() {
        Task task = new Task("Sample Task");
        taskManager.addTask(task);
        taskManager.removeTask(task);
        ObservableList<Task> tasks = taskManager.getTasks();
        assertFalse(tasks.contains(task));
    }

    @Test
    public void testSortingTasks() {
        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("Banana");
        Task task2 = new Task("Apple");
        Task task3 = new Task("Carrot");

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);

        taskManager.sortTasksByDescription();

        ObservableList<Task> sortedTasks = taskManager.getTasks();
        assertEquals("Apple", sortedTasks.get(0).getDescription());
        assertEquals("Banana", sortedTasks.get(1).getDescription());
        assertEquals("Carrot", sortedTasks.get(2).getDescription());
    }
    @Test
    public void testDatabaseTask() {
        TaskDAO taskDAO = new TaskDAO();
        Task task = new Task("Database Task");

        taskDAO.addTask(task);

        // Retrieve all tasks and check if the added task is there
        List<Task> tasks = taskDAO.getAllTasks();
        boolean found = tasks.stream().anyMatch(t -> t.getDescription().equals("Database Task"));
        assertTrue(found);
    }
    @Test
    public void testConcurrencyInLoadingTasks() {
        TaskManager taskManager = new TaskManager();

        // Assume tasks are loaded in a background thread
        ObservableList<Task> tasks = taskManager.getTasks();

        // Tasks should eventually be loaded
        assertNotNull(tasks);
        assertTrue(tasks.isEmpty());  // Assuming there are tasks in the database
    }
    @Test
    public void testStreamFilterTasks() {
        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Another Task");

        taskManager.addTask(task1);
        taskManager.addTask(task2);

        // Filter tasks containing "Task"
        List<Task> filteredTasks = taskManager.getTasks().stream()
                .filter(task -> task.getDescription().contains("Task"))
                .collect(Collectors.toList());

        assertEquals(2, filteredTasks.size());  // Both tasks contain "Task"
    }

}
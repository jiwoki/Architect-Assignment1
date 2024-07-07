package edu.sdccd.cisc191.template;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;

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
    public void testLoadTasks() throws IOException {
        try (FileWriter writer = new FileWriter("ToDo.txt")) {
            writer.write("Sample Task 2");
        }
        taskManager = new TaskManager();  // Reload tasks from the file
        ObservableList<Task> tasks = taskManager.getTasks();
        assertEquals(1, tasks.size());
        assertEquals("Sample Task 2", tasks.get(0).getDescription());
    }
}
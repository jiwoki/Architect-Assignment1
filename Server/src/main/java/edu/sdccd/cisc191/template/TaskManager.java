package edu.sdccd.cisc191.template;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

/*
 * Model for managing the list of tasks in the Application.
 * Controls adding and removing a task to the task list.
 * Also saving and retrieving the task list from the file.
 */

public class TaskManager {
    private ObservableList<Task> tasks; // List to store tasks and update UI
    private static final String FILE_PATH = "ToDo.txt"; // File path for saving and loading Task list

    // Constructor to initialize TaskManager and load tasks from the file
    public TaskManager() {
        tasks = FXCollections.observableArrayList();
        loadTasks();
    }
    // Getter method for list of tasks
    public ObservableList<Task> getTasks() {
        return tasks;
    }
    // add task to list and update file
    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }
    // remove task from list and update file
    public void removeTask(Task task) {
        tasks.remove(task);
        saveTasks();
    }
    // Save list of tasks and update file
    private void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.getDescription());
                writer.newLine();
            }
        }
        // If exception occurs, print stack trace error
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load list of tasks from the file
    private void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String taskDescription;
            while ((taskDescription = reader.readLine()) != null) {
                tasks.add(new Task(taskDescription));
            }
        }
        // If exception occurs, print stack trace error
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}

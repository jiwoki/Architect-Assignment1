package edu.sdccd.cisc191.template;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Model for managing the list of tasks in the Application.
 * Controls adding and removing a task to the task list.
 * Also saving and retrieving the task list from the file.
 */

public class TaskManager {

    private CustomLinkedList taskList = new CustomLinkedList(); // Linked list to store tasks
    private TaskDAO taskDAO = new TaskDAO(); // Data Access Object for database operations
    private Runnable onTasksLoaded;  // Listener to notify when tasks are loaded

    // Constructor to initialize TaskManager and load tasks concurrently
    public TaskManager() {
        loadTasksConcurrently();
    }

    // Method to register a listener for when tasks are loaded
    public void setOnTasksLoaded(Runnable onTasksLoaded) {
        this.onTasksLoaded = onTasksLoaded;
    }

    // Method to load tasks from the database in a background thread
    private void loadTasksConcurrently() {
        javafx.concurrent.Task<ObservableList<Task>> loadTask = new javafx.concurrent.Task<ObservableList<Task>>() {
            @Override
            protected ObservableList<Task> call() throws Exception {
                ObservableList<Task> tasks = FXCollections.observableArrayList(taskDAO.getAllTasks());
                return tasks;
            }
        };

        // When the task completes, update the linked list and UI
        loadTask.setOnSucceeded(event -> {
            ObservableList<Task> tasks = loadTask.getValue();
            taskList = new CustomLinkedList();  // Reset the linked list
            tasks.forEach(taskList::add);  // Add each task to the linked list

            if (onTasksLoaded != null) {
                onTasksLoaded.run();  // Notify that tasks are loaded
            }
        });

        // Handle any failure in loading tasks
        loadTask.setOnFailed(event -> {
            System.err.println("Failed to load tasks from the database.");
            loadTask.getException().printStackTrace();
        });

        // Start the task in a new thread
        new Thread(loadTask).start();
    }

    // Returns the list of tasks as an ObservableList
    public ObservableList<Task> getTasks() {
        return taskList.toObservableList();
    }

    // Adds a new task to the linked list and the database.
    public void addTask(Task task) {
        taskList.add(task);
        taskDAO.addTask(task);
    }
    // Updates an existing task in the database.
    public void updateTask(Task task) {
        taskDAO.updateTask(task);
    }

    // Removes a task from the linked list and the database.
    public void removeTask(Task task) {
        taskList.remove(task);
        taskDAO.deleteTask(task.getId());
    }

    // Sorts the tasks by their description in alphabetical order and updates the linked list with the sorted tasks.
    public void sortTasksByDescription() {
        ObservableList<Task> sortedTasks = FXCollections.observableArrayList(
                getTasks().stream()
                        .sorted(Comparator.comparing(Task::getDescription))
                        .collect(Collectors.toList())
        );

        // Update the linked list with the sorted tasks
        taskList = new CustomLinkedList();
        sortedTasks.forEach(taskList::add);
    }
}

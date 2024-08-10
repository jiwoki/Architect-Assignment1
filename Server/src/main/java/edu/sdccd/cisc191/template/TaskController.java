package edu.sdccd.cisc191.template;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Creates and controls the communication between the TaskView class and TaskManager class.
 * Handles adding and removing tasks to the tasklist based on user input from the UI.
 */

public class TaskController {
    private final TaskManager model; // Reference to the TaskManager Class
    private final TaskView view; // Reference to the TaskView class

    // Constructor to initialize controller with model and view
    public TaskController(TaskManager model, TaskView view) {
        this.model = model;
        this.view = view;

        // Register a listener to update the UI when tasks are loaded from the database
        model.setOnTasksLoaded(() -> {
            view.getTaskList().setItems(model.getTasks());  // Refresh the list once tasks are loaded
        });

        // binds the model's lists of tasks to the list view
        view.getTaskList().setItems(model.getTasks());

        view.getAddButton().setOnAction(e -> addTask(new Task(""))); // Set adding task action for add button
        view.getDeleteButton().setOnAction(e -> deleteTask(new Task(""))); // Set removing task action for delete button
        view.getSortButton().setOnAction(e -> sortTasks()); // Set sort tasks action for the Sort button
    }

    // Method for adding task via add button
    private void addTask(Task task) {
        String taskDescription = view.getTaskInput().getText();
        if (!taskDescription.isEmpty()) {
            model.addTask(new Task(taskDescription));
            view.getTaskInput().clear();
            view.getTaskList().setItems(model.getTasks()); // Refresh the list

        }
    }

    // Method for removing task via delete button
    private void deleteTask(Task task) {
        Task selectedTask = view.getTaskList().getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            model.removeTask(selectedTask);
            view.getTaskList().setItems(model.getTasks()); // Refresh the list
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Task was not selected. Select a task to delete.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    // Method for sorting tasks by their description when the sort button is clicked.
    private void sortTasks() {
        model.sortTasksByDescription();
        view.getTaskList().setItems(model.getTasks()); // Refresh the list
    }
} // end TaskController class

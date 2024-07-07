package edu.sdccd.cisc191.template;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/*
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

        // binds the model's lists of tasks to the list view
        view.getTaskList().setItems(model.getTasks());

        view.getAddButton().setOnAction(e -> addTask(new Task(""))); // Set adding task action for add button
        view.getDeleteButton().setOnAction(e -> deleteTask(new Task(""))); // Set removing task action for delete button

    }

    // Method for adding task via add button
    private void addTask(Task task) {
        String taskDescription = view.getTaskInput().getText();
        if (!taskDescription.isEmpty()) {
            model.addTask(new Task(taskDescription));
            view.getTaskInput().clear();
        }
    }

    // Method for removing task via delete button
    private void deleteTask(Task task) {
        Task selectedTask = view.getTaskList().getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            model.removeTask(selectedTask);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Task was not selected. Select a task to delete.", ButtonType.OK);
            alert.showAndWait();
        }
    }
} // end TaskController class

package edu.sdccd.cisc191.template;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * Creates and manages the UI for the application.
 * End users can add and remove tasks to the tasklist through the graphical interface
 */

public class TaskView {
    private TextField taskInput; // Text field for inputting new task
    private Button addButton; // Button for adding task
    private Button deleteButton; // Button for removing task
    private ListView<Task> taskList; // List view for displaying all tasks

    // Constructor for JavaFX components
    public TaskView(Stage stage) {
        VBox root = new VBox(); // Vertical box display component

        taskInput = new TextField(); // Initialize text field
        taskInput.setId("taskInput"); // For testing purposes

        addButton = new Button("ADD"); // Initialize add button
        deleteButton = new Button("Delete"); // Initialize delete button
        taskList = new ListView<>(); // Initialize list view


        // add components to vertical box layout
        root.getChildren().addAll(taskInput, addButton, deleteButton, taskList);

        Scene scene = new Scene(root, 300, 400); // Initialize scene with the vertical box layout
        stage.setTitle("ToDo List"); // set the title of the stage
        stage.setScene(scene); // attach the scene to the stage
        stage.show(); // display the stage
    }
    // Getter method for the task in the input field
    public TextField getTaskInput() {
        return taskInput;
    }
    // Getter method for add button
    public Button getAddButton() {
        return addButton;
    }
    // Getter method for delete button
    public Button getDeleteButton() {
        return deleteButton;
    }
    // Getter method for the task list view
    public ListView<Task> getTaskList() {
        return taskList;
    }
} // end TaskView class

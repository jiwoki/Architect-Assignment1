package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class to launch the application.
 * Initializes the TaskManager, TaskView, and TaskController.
 * Then starts the JavaFX interface.
 */
public class Server extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        TaskManager model = new TaskManager(); // Model: Manages the data and business logic
        TaskView view = new TaskView(stage);   // View: Manages the user interface
        new TaskController(model, view);       // Controller: Handles the interaction between Model and View
    }

    // launches application
    public static void main(String[] args) {
        launch(args);
    }
} // ends Server class

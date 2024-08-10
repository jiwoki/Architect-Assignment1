package edu.sdccd.cisc191.template;

/**
 * Represents a task in the To-Do List Application.
 * Each task has a description that can be assigned and retrieved.
 */

public class Task {

    private Long id;  // Unique identifier for each task
    private String description;  // Task description

    // Default constructor
    public Task() {}

    // Constructor to initialize Task with description
    public Task(String description) {
        this.description = description;
    }

    // Gets the unique identifier of the task.
    public Long getId() {
        return id;
    }
    // Sets the unique identifier of the task.
    public void setId(Long id) {
        this.id = id;
    }

    // Gets the description of the desk
    public String getDescription() {
        return description;
    }
    // Sets the description of the tas
    public void setDescription(String description) {
        this.description = description;
    }

    // Overridden method to return the string representation of the task.
    @Override
    public String toString() {
        return description;
    }
}


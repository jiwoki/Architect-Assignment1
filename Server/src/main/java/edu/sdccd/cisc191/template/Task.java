package edu.sdccd.cisc191.template;

/*
 * Represents a task in the To-Do List Application.
 * Each task has a description that can be assigned and retrieved.
 */

public class Task {

    private String description; // variable to store task description


    // Constructor to initialize Task with description
    public Task(String description) {
        this.description = description;
    }
    // Getter method for Task
    public String getDescription() {
        return description;
    }
    // Setter method for Task
    public void setDescription(String taskDescription) {
        this.description = taskDescription;
    }
    // Overridden method to return Task info
    @Override
    public String toString() {
        return description;
    }

} // end Task class

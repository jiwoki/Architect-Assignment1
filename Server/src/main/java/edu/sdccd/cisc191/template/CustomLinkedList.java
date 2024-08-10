package edu.sdccd.cisc191.template;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * CustomLinkedList is a singly linked list
 * that stores Task objects. It provides basic operations to add and remove tasks
 * from the list, as well as a method to convert the list into an ObservableList
 * for use with JavaFX.
 */
public class CustomLinkedList {
    private Node head;  // Reference to the first node in the linked list

    // Adds a task to the end of the linked list.
    public void add(Task task) {
        Node newNode = new Node(task);  // Create a new node with the given task
        if (head == null) {
            // If the list is empty, set the head to the new node
            head = newNode;
        } else {
            // Traverse to the end of the list and append the new node
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Removes a task from the linked list by its Task object.
    public void remove(Task task) {
        if (head == null) return;  // If the list is empty, do nothing

        // If the head is the task to be removed, update the head reference
        if (head.data.equals(task)) {
            head = head.next;
            return;
        }

        // Traverse the list to find and remove the task
        Node current = head;
        while (current.next != null && !current.next.data.equals(task)) {
            current = current.next;
        }

        // If the task is found, remove it by skipping over the node
        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    // Converts the linked list into an ObservableList for use with JavaFX UI
    public ObservableList<Task> toObservableList() {
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        Node current = head;

        // Traverse the linked list and add each task to the ObservableList
        while (current != null) {
            tasks.add(current.data);
            current = current.next;
        }
        return tasks;
    }
}
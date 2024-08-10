package edu.sdccd.cisc191.template;

/**
 * Node is a simple class that represents a single element in a linked list.
 * Each Node contains a reference to a Task object (the data) and a reference to the next Node in the list.
 */
class Node {
    Task data;  // The Task object stored in this node
    Node next;  // Reference to the next node in the linked list

    /**
     * Constructs a Node with the specified Task.
     *
     * @param data The Task to be stored in this node.
     */
    public Node(Task data) {
        this.data = data;  // Initialize the node with the provided Task
        this.next = null;  // Initially, the next node is set to null
    }
}

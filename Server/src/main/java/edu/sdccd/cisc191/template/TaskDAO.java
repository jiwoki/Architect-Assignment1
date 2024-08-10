package edu.sdccd.cisc191.template;

import java.sql.*;
import java.util.ArrayList;

/**
 * TaskDAO (Data Access Object) is responsible for managing the persistence of Task objects.
 * It provides methods to add, retrieve, update, and delete tasks in the database.
 * This class interacts with the database using JDBC (Java Database Connectivity).
 */

public class TaskDAO {

    // Adds a new task to the database. The task description is inserted, and the generated ID is set in the Task object.
    public void addTask(Task task) {
        String sql = "INSERT INTO tasks (description) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set the description parameter
            pstmt.setString(1, task.getDescription());

            // Execute the update
            pstmt.executeUpdate();

            // Retrieve the generated keys and set the ID in the task object
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    task.setId(rs.getLong(1));  // Set the generated ID
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieves all tasks from the database.
    public ArrayList<Task> getAllTasks() {
        String sql = "SELECT * FROM tasks";
        ArrayList<Task> tasks = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Iterate through the result set and create Task objects
            while (rs.next()) {
                Task task = new Task(rs.getString("description"));
                task.setId(rs.getLong("id"));
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    // Updates the description of an existing task in the database.
    public void updateTask(Task task) {
        String sql = "UPDATE tasks SET description = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, task.getDescription());
            pstmt.setLong(2, task.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Deletes a task from the database by its ID.
    public void deleteTask(Long taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, taskId);
            pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

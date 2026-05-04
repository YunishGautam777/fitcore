package dao;

import java.sql.*;
import java.util.*;
import util.DBUtil;
import model.Trainer;

/**
 * Data Access Object for Trainer entities.
 * Handles all database operations related to trainers.
 */
public class TrainerDao {

    /**
     * Adds a new trainer to the database.
     * @param t The Trainer object to add
     * @throws SQLException if a database error occurs
     */
    public void addTrainer(Trainer t) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO trainers (name, email, phone, specialization) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, t.getName());
            stmt.setString(2, t.getEmail());
            stmt.setString(3, t.getPhone());
            stmt.setString(4, t.getSpecialization());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    /**
     * Retrieves all trainers from the database.
     * @return List of all Trainer objects
     * @throws SQLException if a database error occurs
     */
    public List<Trainer> getAllTrainers() throws SQLException {
        List<Trainer> trainers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM trainers";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Trainer t = new Trainer();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                t.setEmail(rs.getString("email"));
                t.setPhone(rs.getString("phone"));
                t.setSpecialization(rs.getString("specialization"));
                trainers.add(t);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return trainers;
    }

    /**
     * Updates an existing trainer in the database.
     * @param t The Trainer object with updated information
     * @throws SQLException if a database error occurs
     */
    public void updateTrainer(Trainer t) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE trainers SET name=?, email=?, phone=?, specialization=? WHERE id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, t.getName());
            stmt.setString(2, t.getEmail());
            stmt.setString(3, t.getPhone());
            stmt.setString(4, t.getSpecialization());
            stmt.setInt(5, t.getId());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    /**
     * Deletes a trainer from the database by ID.
     * @param id The ID of the trainer to delete
     * @throws SQLException if a database error occurs
     */
    public void deleteTrainer(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM trainers WHERE id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}

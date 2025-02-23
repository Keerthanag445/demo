package org.cyberscam.dao;
import org.cyberscam.config.DatabaseConfig;
import org.cyberscam.models.Incident;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class IncidentDAO {

    public boolean ReportIncident(Incident incident) {
        String sql = "INSERT INTO Incidents (user_id, category_id, title, description, status,  evidence_attachment, ) VALUES (?, ?, ?, ?, 'Pending', ?, )";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, incident.getUserId());
            stmt.setInt(2, incident.getCategoryId());
            stmt.setString(3, incident.getTitle());
            stmt.setString(4, incident.getDescription());
            stmt.setString(5, incident.getEvidencePath());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ValidateIncident(int incidentId, String newStatus) {
        String sql = "UPDATE Incidents SET status = ? WHERE incident_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, incidentId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Incident> getAllIncidents() {
        List<Incident> incidents = new ArrayList<>();
        String sql = "SELECT * FROM Incidents";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Incident incident = new Incident();
                incident.setIncidentId(rs.getInt("incident_id"));
                incident.setUserId(rs.getInt("user_id"));
                incident.setCategoryId(rs.getInt("category_id"));
                incident.setTitle(rs.getString("title"));
                incident.setDescription(rs.getString("description"));
                incident.setEvidencePath(rs.getString("evidence_attachment"));
                incident.setStatus(rs.getString("status"));
                incidents.add(incident);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incidents;
    }

    public List<Incident> getValidatedIncidents() {
        List<Incident> incidents = new ArrayList<>();
        String sql = "SELECT * FROM Incidents WHERE status = 'Validated'";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Incident incident = new Incident();
                incident.setIncidentId(rs.getInt("incident_id"));
                incident.setUserId(rs.getInt("user_id"));
                incident.setTitle(rs.getString("title"));
                incident.setDescription(rs.getString("description"));
                incident.setCategoryId(rs.getInt("category_id"));
                incident.setEvidencePath(rs.getString("evidence_path"));
                incident.setStatus(rs.getString("status"));
                incidents.add(incident);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incidents;
    }
}

package org.cybersecurity;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class IncidentReg{

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
}
    class Main {
        public static void main(String[] args) {
            IncidentReg incidentReg = new IncidentReg();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. Report New Incident");
                System.out.println("2. View All Reported Incidents");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter User ID: ");
                        int UserId = scanner.nextInt();
                        System.out.println("Enter Incident Title: ");
                        String Title = scanner.nextLine();

                        System.out.println("Enter Incident Description: ");
                        String Description = scanner.nextLine();
                        System.out.println("Enter Category ID (1: Phishing, 2: Malware, 3: Identity Theft): ");
                        int CategoryId = scanner.nextInt();
                        System.out.println("Enter Evidence File Path: ");
                        String EvidencePath = scanner.nextLine();
                        if (incidentReg.ReportIncident(new Incident(UserId,Title,Description,CategoryId,EvidencePath))){
                            System.out.println("Incident registered successfull");
                        }
                        else{
                            System.out.println("Couldnt register incident");
                        }
                        break;
                    case 2:
                        List<Incident> incidents = incidentReg.getAllIncidents();
                        if (incidents.isEmpty()) {
                            System.out.println("No incidents reported yet.");
                        } else {
                            System.out.println("\n=== Reported Incidents ===");
                            for (Incident incident : incidents) {
                                System.out.println("Incident ID: " + incident.getIncidentId());
                                System.out.println("User ID: " + incident.getUserId());
                                System.out.println("Category ID: " + incident.getCategoryId());
                                System.out.println("Title: " + incident.getTitle());
                                System.out.println("Description: " + incident.getDescription());
                                System.out.println("Evidence Path: " + incident.getEvidencePath());
                                System.out.println("Status: " + incident.getStatus());
                                System.out.println("--------------------------------");
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            }
        }

}
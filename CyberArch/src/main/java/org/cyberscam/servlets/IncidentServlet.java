package org.cyberscam.servlets;

import com.google.gson.Gson;
import org.cyberscam.models.Incident;
import org.cyberscam.services.IncidentServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/incidents/*")
public class IncidentServlet extends HttpServlet {
    private IncidentServices incidentServices = new IncidentServices();
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        StringBuilder jsonBuilder = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        String json = jsonBuilder.toString();

        if (path == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Endpoint not specified");
            return;
        }

        // Handle reporting an incident
        if (path.equals("/report")) {
            Incident incident = gson.fromJson(json, Incident.class);
            boolean success = incidentServices.ReportIncident(incident);
            if (success) {
                response.setStatus(HttpServletResponse.SC_CREATED);
                out.print("{\"message\":\"Incident reported successfully\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"message\":\"Failed to report incident\"}");
            }
        }

        else if (path.equals("/validate")) {
            ValidationRequest req = gson.fromJson(json, ValidationRequest.class);
            boolean updated = incidentServices.validateIncident(req.getIncidentId(), req.getNewStatus());
            if (updated) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.print("{\"message\":\"Incident status updated successfully\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"message\":\"Failed to update incident status\"}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Endpoint not found");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        if (path != null && path.equals("/all")) {
            List<Incident> incidents = incidentServices.getAllIncidents();
            response.setStatus(HttpServletResponse.SC_OK);
            out.print(gson.toJson(incidents));
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Endpoint not found");
        }
    }


    private static class ValidationRequest {
        private int incidentId;
        private String newStatus; // Expected to be "Validated" or "Rejected"
        public int getIncidentId() {
            return incidentId;
        }
        public void setIncidentId(int incidentId) {
            this.incidentId = incidentId;
        }
        public String getNewStatus() {
            return newStatus;
        }
        public void setNewStatus(String newStatus) {
            this.newStatus = newStatus;
        }
    }
}

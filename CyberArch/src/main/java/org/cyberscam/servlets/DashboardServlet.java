package org.cyberscam.servlets;

import com.google.gson.Gson;
import org.cyberscam.models.Incident;
import org.cyberscam.services.IncidentServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/dashboard")
public class DashboardServlet extends HttpServlet {
    private IncidentServices incidentServices = new IncidentServices();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Incident> validatedIncidents = incidentServices.getValidatedIncidents();
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(validatedIncidents));
    }
}


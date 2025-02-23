package org.cyberscam.services;
import org.cyberscam.dao.IncidentDAO;
import org.cyberscam.models.Incident;
import java.util.List;
public class IncidentServices {
    private IncidentDAO incidentDAO = new IncidentDAO();

    public boolean ReportIncident(Incident incident){
        return incidentDAO.ReportIncident(incident);
    }

    public boolean validateIncident(int incidentId, String newStatus) {
        return incidentDAO.ValidateIncident(incidentId, newStatus);
    }

    public List<Incident> getAllIncidents(){
        return incidentDAO.getAllIncidents();
    }

    public List<Incident> getValidatedIncidents() {
        return incidentDAO.getValidatedIncidents();
    }
}

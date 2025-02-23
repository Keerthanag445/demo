package org.cyberscam.services;

import org.cyberscam.dao.InformationDAO;
import org.cyberscam.models.Information;
import java.util.List;

public class InformationServices {
    private InformationDAO infoDAO = new InformationDAO();

    public List<Information> getAllInformation() {
        return infoDAO.getAllInformation();
    }
}

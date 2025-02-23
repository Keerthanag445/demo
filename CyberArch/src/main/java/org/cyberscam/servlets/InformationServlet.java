package org.cyberscam.servlets;

import com.google.gson.Gson;
import org.cyberscam.models.Information;
import org.cyberscam.services.InformationServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/information")
public class InformationServlet extends HttpServlet {
    private InformationServices infoService = new InformationServices();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Information> infoList = infoService.getAllInformation();
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(infoList));
    }
}

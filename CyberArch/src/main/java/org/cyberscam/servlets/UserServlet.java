package org.cyberscam.servlets;

import com.google.gson.Gson;
import org.cyberscam.models.User;
import org.cyberscam.dao.UserDA0;
import org.cyberscam.services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/api/users/*")
public class UserServlet extends HttpServlet {
    private UserServices userService = new UserServices();
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getPathInfo();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        BufferedReader reader = request.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        String json = jsonBuilder.toString();

        if (path == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid endpoint");
            return;
        }

        switch (path) {
            case "/register":
                User newUser = gson.fromJson(json, User.class);
                boolean registered = userService.RegisterUser(newUser);
                if (registered) {
                    response.setStatus(HttpServletResponse.SC_CREATED);
                    out.print("{\"message\":\"User registered successfully\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.print("{\"message\":\"User registration failed\"}");
                }
                break;

            case "/login":
                User loginUser = gson.fromJson(json, User.class);
                User loggedIn = userService.LoginUser(loginUser.getEmail(), loginUser.getPassword());
                if (loggedIn != null) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print(gson.toJson(loggedIn));
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    out.print("{\"message\":\"Invalid email or password\"}");
                }
                break;

            case "/resetPassword":
                ResetPasswordRequest prr = gson.fromJson(json, ResetPasswordRequest.class);
                boolean updated = userService.UpdatePassword(prr.getEmail(), prr.getNewPassword());
                if (updated) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print("{\"message\":\"Password updated successfully\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.print("{\"message\":\"Password update failed\"}");
                }
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Endpoint not found");
        }
    }

    private static class ResetPasswordRequest {
        private String email;
        private String newPassword;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getNewPassword() { return newPassword; }
        public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
    }
}
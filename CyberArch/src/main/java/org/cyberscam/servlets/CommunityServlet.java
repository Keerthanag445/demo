package org.cyberscam.servlets;

import com.google.gson.Gson;
import org.cyberscam.models.Question;
import org.cyberscam.models.Answer;
import org.cyberscam.services.CommunityServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/community/*")
public class CommunityServlet extends HttpServlet {
    private CommunityServices communityServices = new CommunityServices();
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No endpoint specified");
            return;
        }

        if (path.equals("/postQuestion")) {
            Question question = gson.fromJson(json, Question.class);
            boolean success = communityServices.postQuestion(question);
            if (success) {
                response.setStatus(HttpServletResponse.SC_CREATED);
                out.print("{\"message\":\"Question posted successfully\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"message\":\"Failed to post question\"}");
            }
        } else if (path.equals("/postAnswer")) {
            Answer answer = gson.fromJson(json, Answer.class);
            boolean success = communityServices.postAnswer(answer);
            if (success) {
                response.setStatus(HttpServletResponse.SC_CREATED);
                out.print("{\"message\":\"Answer posted successfully\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"message\":\"Failed to post answer\"}");
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

        if (path != null && path.equals("/allQuestions")) {
            List<Question> questions = communityServices.getAllQuestions();
            response.setStatus(HttpServletResponse.SC_OK);
            out.print(gson.toJson(questions));
        } else if (path != null && path.equals("/getAnswers")) {
            String questionIdStr = request.getParameter("questionId");
            if (questionIdStr != null) {
                int questionId = Integer.parseInt(questionIdStr);
                List<Answer> answers = communityServices.getAnswersForQuestion(questionId);
                response.setStatus(HttpServletResponse.SC_OK);
                out.print(gson.toJson(answers));
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing questionId parameter");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Endpoint not found");
        }
    }
}

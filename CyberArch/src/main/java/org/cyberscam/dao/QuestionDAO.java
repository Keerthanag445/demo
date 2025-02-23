package org.cyberscam.dao;

import org.cyberscam.config.DatabaseConfig;
import org.cyberscam.models.Question;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    // Insert a new question
    public boolean postQuestion(Question question) {
        String sql = "INSERT INTO Questions (user_id, question_text, posted_at) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, question.getUserId());
            stmt.setString(2, question.getQuestionText());
            stmt.setString(3, question.getPostedAt());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all questions
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM Questions";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Question question = new Question();
                question.setQuestionId(rs.getInt("question_id"));
                question.setUserId(rs.getInt("user_id"));
                question.setQuestionText(rs.getString("question_text"));
                question.setPostedAt(rs.getString("posted_at"));
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
}

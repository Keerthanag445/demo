package org.cyberscam.dao;

import org.cyberscam.config.DatabaseConfig;
import org.cyberscam.models.Answer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAO {

    public boolean postAnswer(Answer answer) {
        String sql = "INSERT INTO Answers (question_id, user_id, answer_text, posted_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, answer.getQuestionId());
            stmt.setInt(2, answer.getUserId());
            stmt.setString(3, answer.getAnswerText());
            stmt.setString(4, answer.getPostedAt());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Answer> getAnswersByQuestionId(int questionId) {
        List<Answer> answers = new ArrayList<>();
        String sql = "SELECT * FROM Answers WHERE question_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, questionId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Answer answer = new Answer();
                    answer.setAnswerId(rs.getInt("answer_id"));
                    answer.setQuestionId(rs.getInt("question_id"));
                    answer.setUserId(rs.getInt("user_id"));
                    answer.setAnswerText(rs.getString("answer_text"));
                    answer.setPostedAt(rs.getString("posted_at"));
                    answers.add(answer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }
}

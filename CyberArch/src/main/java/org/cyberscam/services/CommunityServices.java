package org.cyberscam.services;

import org.cyberscam.dao.QuestionDAO;
import org.cyberscam.dao.AnswerDAO;
import org.cyberscam.models.Question;
import org.cyberscam.models.Answer;
import java.util.List;

public class CommunityServices {
    private QuestionDAO questionDAO = new QuestionDAO();
    private AnswerDAO answerDAO = new AnswerDAO();

    public boolean postQuestion(Question question) {
        return questionDAO.postQuestion(question);
    }

    public boolean postAnswer(Answer answer) {
        return answerDAO.postAnswer(answer);
    }

    public List<Question> getAllQuestions() {
        return questionDAO.getAllQuestions();
    }

    public List<Answer> getAnswersForQuestion(int questionId) {
        return answerDAO.getAnswersByQuestionId(questionId);
    }
}

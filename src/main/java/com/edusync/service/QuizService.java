package com.edusync.service;

import com.edusync.entity.User;
import com.edusync.request.QuizCreationRequest;
import com.edusync.response.QuizResponse;

import java.util.List;

public interface QuizService {

    QuizResponse createQuiz(QuizCreationRequest request, Integer lessonId, User user);
    QuizResponse updateQuiz(QuizCreationRequest request, Integer lessonId, User user);
    void deleteQuiz(Integer quizId);
    QuizResponse getQuizById(Integer quizId);
    List<QuizResponse> getAllQuizzes(Integer lessonId);


}

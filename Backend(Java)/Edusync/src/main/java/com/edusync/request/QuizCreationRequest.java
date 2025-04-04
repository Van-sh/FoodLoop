package com.edusync.request;

import java.util.List;

public record QuizCreationRequest(
        String title,
        String description,
        List<QuestionCreationRequest> questions
) {
}

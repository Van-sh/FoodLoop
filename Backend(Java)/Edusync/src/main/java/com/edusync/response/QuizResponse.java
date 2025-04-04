package com.edusync.response;

import java.util.List;

public record QuizResponse(
        String title,
        String description,
        List<QuestionResponse> questionResponses
) {
}

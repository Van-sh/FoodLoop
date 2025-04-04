package com.edusync.request;

import java.util.List;

public record QuestionCreationRequest(
        String text,
        String correctAnswer,
        // MCQ, SHORT_ANSWER, TRUE_FALSE
        String questionType,
        List<String> options

) {
}

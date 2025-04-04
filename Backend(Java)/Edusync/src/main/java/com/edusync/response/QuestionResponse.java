package com.edusync.response;

import java.util.List;

public record QuestionResponse(
        String text,
        List<String> options
) {
}

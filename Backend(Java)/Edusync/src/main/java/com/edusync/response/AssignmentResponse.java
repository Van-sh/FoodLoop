package com.edusync.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AssignmentResponse(
        String title,
        String description,
        LocalDate dueDate,
        String content
) {
}

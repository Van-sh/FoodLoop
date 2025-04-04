package com.edusync.request;

import java.time.LocalDate;

public record AssignmentCreationRequest(
        String title,
        String description,
        String content,
        LocalDate dueDate
) {
}

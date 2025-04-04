package com.edusync.response;

import java.time.LocalDateTime;

public record CourseReviewResponse(
        Integer rating,
        String reviewText,
        LocalDateTime createdAt,
        String studentName
) {
}

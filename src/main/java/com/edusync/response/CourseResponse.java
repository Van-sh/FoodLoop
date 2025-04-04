package com.edusync.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record CourseResponse(
        BigDecimal price,
        String title,
        String description,
        List<CategoryResponse> categoryResponses,

        String overview,
        String admissions,
        String academics,
        String financing,
        String studentExperience,

        List<CourseReviewResponse> courseReviews
//        List<LessonResponse> lessons
) {
}

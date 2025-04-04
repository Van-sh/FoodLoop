package com.edusync.request;

import java.math.BigDecimal;
import java.util.List;

public record CourseUpdateRequest(
        BigDecimal price,
        String title,
        String description,
        List<Integer> categoryId,

        String overview,
        String admissions,
        String academics,
        String financing,
        String studentExperience
) {
}

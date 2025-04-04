package com.edusync.request;

import com.edusync.entity.Category;

import java.math.BigDecimal;
import java.util.List;

public record CourseCreationRequest(

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

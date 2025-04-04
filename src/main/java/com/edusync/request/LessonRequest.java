package com.edusync.request;

public record LessonRequest(
        String title,
        String description,
        String videoUrl,
        String content
) {
}

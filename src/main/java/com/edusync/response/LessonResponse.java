package com.edusync.response;

import com.edusync.entity.Assignment;

import java.util.List;

public record LessonResponse(
        String title,
        String description,
        String videoUrl,
        List<DiscussionResponse> discussionResponses,
        List<QuizResponse> quizResponses,
        List<AssignmentResponse> assignmentResponses
) {
}

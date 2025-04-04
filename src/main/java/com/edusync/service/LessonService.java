package com.edusync.service;

import com.edusync.entity.User;
import com.edusync.request.LessonRequest;
import com.edusync.response.LessonResponse;

import java.util.List;

public interface LessonService {

    LessonResponse addLesson(LessonRequest request, Integer courseId, User user);
    LessonResponse updateLesson(LessonRequest request, Integer lessonId, User user);
    LessonResponse getLessonById(Integer lessonId);
    List<LessonResponse> getAllLessonsByCourseId(Integer courseId);
    void deleteLesson(Integer lessonId, User user);

}

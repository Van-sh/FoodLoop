package com.edusync.service;

import com.edusync.entity.User;
import com.edusync.request.AssignmentCreationRequest;
import com.edusync.response.AssignmentResponse;

import java.util.List;

public interface AssignmentService {

    AssignmentResponse createAssignment(AssignmentCreationRequest request, Integer lessonId, User user);
    AssignmentResponse updateAssignment(AssignmentCreationRequest request, Integer lessonId, User user);
    void deleteAssignment(Integer assignmentId, User user);
    AssignmentResponse getAssignmentById(Integer assignmentId, User user);
    List<AssignmentResponse> getAllAssignments(Integer lessonId, User user);

}

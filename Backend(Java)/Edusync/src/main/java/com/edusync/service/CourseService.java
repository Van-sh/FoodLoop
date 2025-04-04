package com.edusync.service;

import com.edusync.entity.User;
import com.edusync.request.CourseCreationRequest;
import com.edusync.request.CourseUpdateRequest;
import com.edusync.response.CourseResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface CourseService {

    CourseResponse createCourse(CourseCreationRequest request, String token);
    CourseResponse updateCourse(CourseUpdateRequest request,Integer courseId, String token) throws UnauthorizedException;
    CourseResponse getCourseById(Integer courseId);
    List<CourseResponse> getAllCourses();
    void deleteCourseById(Integer courseId, String token);
    Page<CourseResponse> searchCourses(String keyword, BigDecimal minPrice, BigDecimal maxPrice,
                                       Integer minRating, List<Integer> categoryIds,
                                       String sortBy, String sortDirection,
                                       int page, int size);
}

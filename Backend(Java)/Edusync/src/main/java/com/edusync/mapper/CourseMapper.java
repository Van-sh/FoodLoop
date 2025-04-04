package com.edusync.mapper;

import com.edusync.entity.Category;
import com.edusync.entity.Course;
import com.edusync.request.CourseCreationRequest;
import com.edusync.response.CategoryResponse;
import com.edusync.response.CourseResponse;
import com.edusync.service.CategoryService;
import com.edusync.service.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseMapper {


    public Course toCourse(CourseCreationRequest request) {
        return Course
                .builder()
                .price(request.price())
                .title(request.title())
                .description(request.description())
                .overview(request.overview())
                .admissions(request.admissions())
                .academics(request.academics())
                .financing(request.financing())
                .studentExperience(request.studentExperience())
                .build();
    }

    public CourseResponse toCourseResponse(Course savedCourse) {
        List<CategoryResponse> categoryResponses = savedCourse
                .getCategories()
                .stream()
                .map(category -> CategoryResponse
                        .builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build())
                .toList();
        return CourseResponse
                .builder()
                .price(savedCourse.getPrice())
                .title(savedCourse.getTitle())
                .description(savedCourse.getDescription())
                .overview(savedCourse.getOverview())
                .admissions(savedCourse.getAdmissions())
                .academics(savedCourse.getAcademics())
                .financing(savedCourse.getFinancing())
                .categoryResponses(categoryResponses)
                .studentExperience(savedCourse.getStudentExperience())
                .build();

    }
}


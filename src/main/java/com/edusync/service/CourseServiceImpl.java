package com.edusync.service;

import com.edusync.entity.Category;
import com.edusync.entity.Course;
import com.edusync.entity.User;
import com.edusync.mapper.CourseMapper;
import com.edusync.repo.CourseRepository;
import com.edusync.request.CourseCreationRequest;
import com.edusync.request.CourseUpdateRequest;
import com.edusync.response.CourseResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final UserService userService;
    private final CategoryServiceImpl categoryService;

    @Override
    public CourseResponse createCourse(CourseCreationRequest request, String token) {

        User user = userService.findByJwtToken(token);
        Course course = courseMapper.toCourse(request);
        course.setTeacher(user);

        List<Category> categories = categoryService.getAllCategoriesByIds(request.categoryId());
        course.setCategories(categories);

        Course savedCourse = courseRepository.save(course);

        return courseMapper.toCourseResponse(savedCourse);
    }

    @Override
    public CourseResponse updateCourse(CourseUpdateRequest request, Integer courseId, String token) throws UnauthorizedException {
        User user = userService.findByJwtToken(token);

        // Ensure the course exists
        Course course = getCourseUnsafeById(courseId);

        // Validate if the user has permission to update the course (Assuming the course has an owner)
        if (!course.getTeacher().getId().equals(user.getId())) {
            throw new UnauthorizedException("You are not allowed to update this course.");
        }

        // Update fields using Optional to avoid multiple if-checks
        Optional.ofNullable(request.price()).ifPresent(course::setPrice);
        Optional.ofNullable(request.title()).ifPresent(course::setTitle);
        Optional.ofNullable(request.description()).ifPresent(course::setDescription);
        Optional.ofNullable(request.overview()).ifPresent(course::setOverview);
        Optional.ofNullable(request.academics()).ifPresent(course::setAcademics);
        Optional.ofNullable(request.admissions()).ifPresent(course::setAdmissions);
        Optional.ofNullable(request.financing()).ifPresent(course::setFinancing);
        Optional.ofNullable(request.studentExperience()).ifPresent(course::setStudentExperience);

        // Update categories if present
        if (request.categoryId() != null) {
            List<Category> newCategories = categoryService.getAllCategoriesByIds(request.categoryId());
            if (newCategories.isEmpty()) {
                throw new IllegalArgumentException("Invalid category IDs provided.");
            }
            course.setCategories(newCategories);
        }

        // Save and return updated course
        Course updatedCourse = courseRepository.save(course);
        return courseMapper.toCourseResponse(updatedCourse);
    }

    private Course getCourseUnsafeById(Integer courseId) {
        return courseRepository
                .findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));
    }

    @Override
    public CourseResponse getCourseById(Integer courseId) {
        Course course = getCourseUnsafeById(courseId);
        return courseMapper.toCourseResponse(course);
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepository
                .findAll()
                .stream()
                .map(courseMapper::toCourseResponse)
                .toList();
    }

    @Override
    public void deleteCourseById(Integer courseId, String token) {
        getCourseUnsafeById(courseId);
        courseRepository.deleteById(courseId);
    }

    @Override
    public Page<CourseResponse> searchCourses(String keyword, BigDecimal minPrice, BigDecimal maxPrice,
                                              Integer minRating, List<Integer> categoryIds,
                                              String sortBy, String sortDirection,
                                              int page, int size) {

        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        System.out.println("Searching courses with filters:");
        System.out.println("Keyword: " + keyword);
        System.out.println("Min Price: " + minPrice);
        System.out.println("Max Price: " + maxPrice);
        System.out.println("Min Rating: " + minRating);
        System.out.println("Category IDs: " + categoryIds);
        System.out.println("Sort By: " + sortBy + ", Direction: " + direction);


        Specification<Course> spec = CourseSpecification.searchCourses(keyword, minPrice, maxPrice, minRating, categoryIds);

        Page<Course> coursePage = courseRepository.findAll(spec, pageable);

        List<CourseResponse> courseResponses = coursePage.getContent()
                .stream()
                .map(courseMapper::toCourseResponse)
                .toList();

        return new PageImpl<>(courseResponses, pageable, coursePage.getTotalElements());
    }
}

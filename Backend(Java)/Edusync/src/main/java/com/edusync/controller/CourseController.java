package com.edusync.controller;

import com.edusync.request.CourseCreationRequest;
import com.edusync.request.CourseUpdateRequest;
import com.edusync.response.CourseResponse;
import com.edusync.service.CourseService;
import com.edusync.service.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/teacher/create")
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseCreationRequest request,
                                                       @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(courseService.createCourse(request, token));
    }

    @PutMapping("/teacher/update/{courseId}")
    public ResponseEntity<CourseResponse> updateCourse(@RequestBody CourseUpdateRequest request,
                                                       @RequestHeader("Authorization") String token,
                                                       @PathVariable Integer courseId) throws UnauthorizedException {
        return ResponseEntity.ok(courseService.updateCourse(request, courseId, token));
    }

    @GetMapping("/user/{courseId}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Integer courseId) {
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @GetMapping("/user")
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @DeleteMapping("/teacher/delete/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Integer courseId,
                                               @RequestHeader("Authorization") String token) {
        courseService.deleteCourseById(courseId, token);
        return ResponseEntity.ok("Deleted course with id " + courseId + " successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<Page<CourseResponse>> searchCourses(
            @RequestParam(required = false, defaultValue = "") String keyword,
            @RequestParam(required = false, defaultValue = "0") BigDecimal minPrice,
            @RequestParam(required = false, defaultValue = "999999") BigDecimal maxPrice,
            @RequestParam(required = false, defaultValue = "0") Integer minRating,
            @RequestParam(required = false) List<Integer> categoryIds, // Accepts multiple category IDs
            @RequestParam(required = false, defaultValue = "title") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {

        return ResponseEntity.ok(courseService.searchCourses(keyword, minPrice, maxPrice, minRating, categoryIds, sortBy, sortDirection, page, size));
    }

}

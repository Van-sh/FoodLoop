package com.edusync.repo;

import com.edusync.entity.Category;
import com.edusync.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    // Search by course title (case-insensitive)
    Page<Course> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    // Search by category name using JPQL
    @Query("SELECT c FROM Course c JOIN c.categories cat WHERE LOWER(cat.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Course> findByCategoryName(@Param("keyword") String keyword, Pageable pageable);

    // Filtering by price range & rating
    @Query("SELECT c FROM Course c WHERE c.price BETWEEN :minPrice AND :maxPrice AND c.rating >= :minRating")
    Page<Course> filterCourses(@Param("minPrice") BigDecimal minPrice,
                               @Param("maxPrice") BigDecimal maxPrice,
                               @Param("minRating") Integer minRating,
                               Pageable pageable);

    Page<Course> findAll(Specification<Course> spec, Pageable pageable);
}

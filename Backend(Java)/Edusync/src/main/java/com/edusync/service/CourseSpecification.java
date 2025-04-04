package com.edusync.service;

import com.edusync.entity.Category;
import com.edusync.entity.Course;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseSpecification {

    public static Specification<Course> searchCourses(String keyword, BigDecimal minPrice, BigDecimal maxPrice,
                                                      Integer minRating, List<Integer> categoryIds) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Search by course title
            Predicate titlePredicate = cb.like(cb.lower(root.get("title")), "%" + keyword.toLowerCase() + "%");

            // Search by category name
            Join<Course, Category> categoryJoin = root.join("categories", JoinType.LEFT);
            Predicate categoryPredicate = cb.like(cb.lower(categoryJoin.get("name")), "%" + keyword.toLowerCase() + "%");

            // Use OR condition to match either title OR category
            predicates.add(cb.or(titlePredicate, categoryPredicate));

            // Filter by category IDs
            if (categoryIds != null && !categoryIds.isEmpty()) {
                predicates.add(categoryJoin.get("id").in(categoryIds));
            }

            // Filter by price range
            if (minPrice != null && maxPrice != null) {
                predicates.add(cb.between(root.get("price"), minPrice, maxPrice));
            }

            // Filter by minimum rating, treating NULL ratings as 0
            if (minRating != null) {
                predicates.add(cb.greaterThanOrEqualTo(cb.coalesce(root.get("rating"), 0), minRating));
                System.out.println("Applied rating filter: " + minRating);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}

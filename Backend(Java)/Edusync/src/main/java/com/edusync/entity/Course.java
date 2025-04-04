package com.edusync.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"categories", "studentsEnrolled", "courseReviews", "lessons"})
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal price;
    private String title;

    @ManyToOne
    private User teacher;

    @Min(1)
    @Max(5)
    private Integer rating;
    private String description;

//  ---------------- Course details --------------
    private String overview;
    private String admissions;
    private String academics;
    private String financing;
    private String studentExperience;
//  -----------------------------------------------

    @ManyToMany
    @JoinTable(
            name = "course_category_mapping",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonManagedReference
    private List<Category> categories;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<Enrollment> studentsEnrolled;

    @OneToMany(mappedBy = "course")
    @JsonManagedReference
    private List<CourseReview> courseReviews;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Lesson> lessons;

}











